from pathlib import Path
import subprocess
import requests
from datetime import datetime, timezone, timedelta
import re

README_PATH = Path("README.md")
ASSETS_PATH = Path("assets")
ASSETS_PATH.mkdir(exist_ok=True)

# =========================
# Timezone (KST)
# =========================
KST = timezone(timedelta(hours=9))

# =========================
# Git util
# =========================

def git_last_commit(path: Path) -> str | None:
    try:
        return subprocess.check_output(
            ["git", "log", "-1", "--format=%cs", "--", str(path)],
            stderr=subprocess.DEVNULL,
            text=True,
        ).strip()
    except:
        return None


def last_commit_from_folders(folders: list[Path]) -> str:
    dates = [d for f in folders if (d := git_last_commit(f))]
    return max(dates) if dates else "N/A"


# =========================
# Scan rules
# =========================

def scan_nested(base: Path):
    if not base.exists():
        return []
    return [
        problem
        for level in base.iterdir() if level.is_dir()
        for problem in level.iterdir() if problem.is_dir()
    ]


def scan_codetree():
    base = Path("Codetree")
    if not base.exists():
        return []
    return [
        problem
        for day in base.iterdir() if day.is_dir()
        for problem in day.iterdir() if problem.is_dir()
    ]


def scan_leetcode():
    base = Path("Leetcode")
    return [p for p in base.iterdir() if p.is_dir()] if base.exists() else []


# =========================
# External API
# =========================

def get_baekjoon_total(handle):
    try:
        r = requests.get(
            f"https://solved.ac/api/v3/user/show?handle={handle}",
            timeout=5
        )
        data = r.json()
        return data.get("solvedCount", 0)
    except:
        return 0


def get_language_stats():
    try:
        r = requests.get(
            "https://api.github.com/repos/gidaseul/Algorithm-Solutions/languages",
            timeout=5
        )
        data = r.json()
        total = sum(data.values())
        return {
            k: round(v / total * 100, 1)
            for k, v in data.items()
        }
    except:
        return {}


# =========================
# SVG Progress Bar
# =========================

def generate_progress_svg(name, current, total, color, filename):
    percent = 0 if total == 0 else round(current / total * 100, 1)
    width = 700
    bar_width = int(width * percent / 100)

    svg = f"""
<svg width="{width}" height="90" xmlns="http://www.w3.org/2000/svg">
  <rect width="{width}" height="25" fill="#2c2f33" rx="12"/>
  <rect width="{bar_width}" height="25" fill="{color}" rx="12">
    <animate attributeName="width"
             from="0"
             to="{bar_width}"
             dur="1.2s"
             fill="freeze" />
  </rect>
  <text x="0" y="65" fill="white" font-size="20">
    {name}  {current} / {total}  ({percent}%)
  </text>
</svg>
"""
    (ASSETS_PATH / filename).write_text(svg)


# =========================
# Stats computation
# =========================

def compute_stats():
    stats = {}

    bj = scan_nested(Path("백준"))
    bj_repo = len(bj)
    bj_total = get_baekjoon_total("hye0328")

    generate_progress_svg(
        "Baekjoon",
        bj_repo,
        bj_total,
        "#f39c12",
        "bj_progress.svg"
    )

    stats["Baekjoon"] = {
        "count": f"{bj_repo} / {bj_total}",
        "last": last_commit_from_folders(bj),
    }

    # Other platforms (100%)
    for name, folder, color in [
        ("Programmers", Path("프로그래머스"), "#2ecc71"),
        ("SWEA", Path("SWEA"), "#3498db"),
        ("Codetree", Path("Codetree"), "#9b59b6"),
        ("LeetCode", Path("Leetcode"), "#e67e22"),
    ]:
        problems = (
            scan_nested(folder)
            if name != "Codetree"
            else scan_codetree()
            if name == "Codetree"
            else scan_leetcode()
        )

        count = len(problems)

        generate_progress_svg(
            name,
            count,
            count,
            color,
            f"{name.lower()}_progress.svg"
        )

        stats[name] = {
            "count": count,
            "last": last_commit_from_folders(problems),
        }

    return stats


# =========================
# README update
# =========================

def replace_block(text: str, start: str, end: str, content: str) -> str:
    pattern = re.compile(
        f"{re.escape(start)}.*?{re.escape(end)}",
        re.DOTALL
    )
    return pattern.sub(f"{start}\n{content}\n{end}", text)


def update_readme(stats: dict):
    readme = README_PATH.read_text(encoding="utf-8")

    table = [
        "| Platform | Problems | Last Commit |",
        "|---|---:|---|",
    ]

    total = 0
    for platform, data in stats.items():
        table.append(f"| {platform} | {data['count']} | {data['last']} |")
        if isinstance(data["count"], int):
            total += data["count"]
        else:
            total += int(str(data["count"]).split("/")[0].strip())

    lang_stats = get_language_stats()
    lang_md = "\n".join(
        [f"- **{k}**: {v}%" for k, v in lang_stats.items()]
    )

    now_kst = datetime.now(KST).strftime("%Y-%m-%d %H:%M KST")

    readme = replace_block(
        readme, "<!-- STATS:START -->", "<!-- STATS:END -->",
        "\n".join(table)
    )

    readme = replace_block(
        readme, "<!-- TOTAL:START -->", "<!-- TOTAL:END -->",
        f"**Total Problems:** {total}"
    )

    readme = replace_block(
        readme, "<!-- LANG:START -->", "<!-- LANG:END -->",
        lang_md
    )

    readme = replace_block(
        readme, "<!-- UPDATED:START -->", "<!-- UPDATED:END -->",
        f"🕒 Last Auto Update: {now_kst}"
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
