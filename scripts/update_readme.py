from pathlib import Path
import subprocess
import requests
from datetime import datetime, timezone, timedelta
import re

README_PATH = Path("README.md")

# =========================
# Timezone (KST)
# =========================
KST = timezone(timedelta(hours=9))

TIER_MAP = [
    "Unrated",
    "Bronze V", "Bronze IV", "Bronze III", "Bronze II", "Bronze I",
    "Silver V", "Silver IV", "Silver III", "Silver II", "Silver I",
    "Gold V", "Gold IV", "Gold III", "Gold II", "Gold I",
    "Platinum V", "Platinum IV", "Platinum III", "Platinum II", "Platinum I",
    "Diamond V", "Diamond IV", "Diamond III", "Diamond II", "Diamond I",
    "Ruby V", "Ruby IV", "Ruby III", "Ruby II", "Ruby I",
]

# =========================
# Git util
# =========================

def git_last_commit(path: Path) -> str | None:
    try:
        date = subprocess.check_output(
            ["git", "log", "-1", "--format=%cs", "--", str(path)],
            stderr=subprocess.DEVNULL,
            text=True,
        ).strip()
        return date
    except:
        return None


def last_commit_from_folders(folders: list[Path]) -> str:
    dates = []
    for f in folders:
        d = git_last_commit(f)
        if d:
            dates.append(d)
    return max(dates) if dates else "N/A"


# =========================
# Scan rules
# =========================

def scan_codetree():
    base = Path("Codetree")
    problems = []
    if base.exists():
        for day in base.iterdir():
            if day.is_dir():
                for problem in day.iterdir():
                    if problem.is_dir():
                        problems.append(problem)
    return problems


def scan_leetcode():
    base = Path("Leetcode")
    return [p for p in base.iterdir() if p.is_dir()] if base.exists() else []


def scan_nested(base: Path):
    problems = []
    if base.exists():
        for level in base.iterdir():
            if level.is_dir():
                for problem in level.iterdir():
                    if problem.is_dir():
                        problems.append(problem)
    return problems


# =========================
# External API
# =========================

def get_language_stats():
    try:
        r = requests.get(
            "https://api.github.com/repos/gidaseul/Algorithm-Solutions/languages",
            timeout=5
        )
        data = r.json()
        total = sum(data.values())
        percent = {
            k: f"{round(v/total*100,1)}%"
            for k, v in data.items()
        }
        return percent
    except:
        return {}


def get_baekjoon_tier(handle: str):
    try:
        url = f"https://solved.ac/api/v3/user/show?handle={handle}"
        r = requests.get(url, timeout=5)
        data = r.json()
        tier_number = data.get("tier", 0)
        return TIER_MAP[tier_number] if tier_number < len(TIER_MAP) else "Unknown"
    except:
        return "Unavailable"


# =========================
# Stats computation
# =========================

def compute_stats():
    stats = {}

    bj = scan_nested(Path("백준"))
    stats["Baekjoon"] = {
        "count": len(bj),
        "last": last_commit_from_folders(bj),
    }

    pg = scan_nested(Path("프로그래머스"))
    stats["Programmers"] = {
        "count": len(pg),
        "last": last_commit_from_folders(pg),
    }

    sw = scan_nested(Path("SWEA"))
    stats["SWEA"] = {
        "count": len(sw),
        "last": last_commit_from_folders(sw),
    }

    ct = scan_codetree()
    stats["Codetree"] = {
        "count": len(ct),
        "last": last_commit_from_folders(ct),
    }

    lc = scan_leetcode()
    stats["LeetCode"] = {
        "count": len(lc),
        "last": last_commit_from_folders(lc),
    }

    return stats


# =========================
# README update
# =========================

def replace_block(text: str, start: str, end: str, content: str) -> str:
    return text.split(start)[0] + start + "\n" + content + "\n" + end + text.split(end)[1]


def update_readme(stats: dict):
    readme = README_PATH.read_text(encoding="utf-8")

    # -------- Platform Table --------
    table = [
        "| Platform | Problems | Last Commit |",
        "|---|---:|---|",
    ]

    total = 0
    for platform, data in stats.items():
        table.append(f"| {platform} | {data['count']} | {data['last']} |")
        total += data["count"]

    # -------- Language Stats --------
    lang_stats = get_language_stats()
    lang_md = "\n".join([f"- **{k}**: {v}" for k, v in lang_stats.items()])

    # -------- Baekjoon Tier --------
    tier = get_baekjoon_tier("hye0328")

    # -------- Update Time (KST) --------
    now_kst = datetime.now(KST).strftime("%Y-%m-%d %H:%M KST")

    # Replace blocks
    readme = replace_block(
        readme,
        "<!-- STATS:START -->",
        "<!-- STATS:END -->",
        "\n".join(table),
    )

    readme = replace_block(
        readme,
        "<!-- TOTAL:START -->",
        "<!-- TOTAL:END -->",
        f"**Total Problems:** {total}",
    )

    readme = replace_block(
        readme,
        "<!-- LANG:START -->",
        "<!-- LANG:END -->",
        lang_md,
    )

    readme = replace_block(
        readme,
        "<!-- TIER:START -->",
        "<!-- TIER:END -->",
        f"🏆 Baekjoon Tier: **{tier}**",
    )

    readme = replace_block(
        readme,
        "<!-- UPDATED:START -->",
        "<!-- UPDATED:END -->",
        f"🕒 Last Auto Update: {now_kst}",
    )

    README_PATH.write_text(readme, encoding="utf-8")


# =========================
# Main
# =========================

def main():
    stats = compute_stats()
    update_readme(stats)


if __name__ == "__main__":
    main()
