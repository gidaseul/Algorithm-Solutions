from __future__ import annotations

from dataclasses import dataclass
from datetime import datetime, timedelta, timezone
from pathlib import Path
from typing import Callable
from urllib.error import HTTPError, URLError
from urllib.request import Request, urlopen
import json
import re
import subprocess


README_PATH = Path("README.md")
ASSETS_PATH = Path("assets")
ASSETS_PATH.mkdir(exist_ok=True)

KST = timezone(timedelta(hours=9))
DATE_FOLDER_PATTERN = re.compile(r"\d{6}")
USER_AGENT = "Algorithm-Solutions-README-Updater"

SOLVED_AC_TIERS = [
    "Unrated",
    "Bronze V",
    "Bronze IV",
    "Bronze III",
    "Bronze II",
    "Bronze I",
    "Silver V",
    "Silver IV",
    "Silver III",
    "Silver II",
    "Silver I",
    "Gold V",
    "Gold IV",
    "Gold III",
    "Gold II",
    "Gold I",
    "Platinum V",
    "Platinum IV",
    "Platinum III",
    "Platinum II",
    "Platinum I",
    "Diamond V",
    "Diamond IV",
    "Diamond III",
    "Diamond II",
    "Diamond I",
    "Ruby V",
    "Ruby IV",
    "Ruby III",
    "Ruby II",
    "Ruby I",
]


@dataclass(frozen=True)
class PlatformConfig:
    name: str
    color: str
    asset_name: str
    scanner: Callable[[], list[Path]]
    show_total: bool = False


@dataclass(frozen=True)
class PlatformStat:
    name: str
    solved_count: int
    total_count: int
    last_commit: str
    show_total: bool

    @property
    def count_label(self) -> str:
        if self.show_total:
            return f"{self.solved_count} / {self.total_count}"
        return str(self.solved_count)


def safe_fetch_json(url: str) -> dict:
    request = Request(url, headers={"User-Agent": USER_AGENT})

    try:
        with urlopen(request, timeout=5) as response:
            return json.load(response)
    except (HTTPError, URLError, TimeoutError, json.JSONDecodeError):
        return {}


def git_last_commit(path: Path) -> str | None:
    try:
        return subprocess.check_output(
            ["git", "log", "-1", "--format=%cs", "--", str(path)],
            stderr=subprocess.DEVNULL,
            text=True,
        ).strip()
    except subprocess.CalledProcessError:
        return None


def last_commit_from_folders(folders: list[Path]) -> str:
    dates = [date for folder in folders if (date := git_last_commit(folder))]
    return max(dates) if dates else "N/A"


def unique_paths(paths: list[Path]) -> list[Path]:
    seen: set[str] = set()
    unique: list[Path] = []

    for path in paths:
        key = str(path.resolve())
        if key in seen:
            continue
        seen.add(key)
        unique.append(path)

    return unique


def child_dirs(base: Path) -> list[Path]:
    if not base.exists():
        return []
    return sorted((path for path in base.iterdir() if path.is_dir()), key=lambda path: path.name)


def scan_two_levels(base: Path) -> list[Path]:
    problems = [
        problem
        for group in child_dirs(base)
        for problem in child_dirs(group)
    ]
    return unique_paths(problems)


def scan_one_level(base: Path) -> list[Path]:
    return unique_paths(child_dirs(base))


def scan_codetree() -> list[Path]:
    date_folders: list[Path] = []

    for root_name in ("Codetree", "CodeTree"):
        date_folders.extend(child_dirs(Path(root_name)))

    date_folders.extend(
        path
        for path in child_dirs(Path("."))
        if DATE_FOLDER_PATTERN.fullmatch(path.name)
    )

    problems = [
        problem
        for date_folder in unique_paths(date_folders)
        for problem in child_dirs(date_folder)
    ]
    return unique_paths(problems)


def get_baekjoon_profile(handle: str) -> dict:
    return safe_fetch_json(f"https://solved.ac/api/v3/user/show?handle={handle}")


def get_language_stats() -> dict[str, float]:
    data = safe_fetch_json("https://api.github.com/repos/gidaseul/Algorithm-Solutions/languages")
    if not data:
        return {}

    total = sum(data.values())
    if total == 0:
        return {}

    return {
        language: round(bytes_used / total * 100, 1)
        for language, bytes_used in sorted(data.items(), key=lambda item: item[1], reverse=True)
    }


def solved_ac_tier_name(tier_value: int | None) -> str | None:
    if tier_value is None:
        return None
    if 0 <= tier_value < len(SOLVED_AC_TIERS):
        return SOLVED_AC_TIERS[tier_value]
    return None


def generate_progress_svg(name: str, current: int, total: int, color: str, filename: str) -> None:
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
    (ASSETS_PATH / filename).write_text(svg.strip() + "\n", encoding="utf-8")


def platform_configs() -> list[PlatformConfig]:
    return [
        PlatformConfig(
            name="Baekjoon",
            color="#f39c12",
            asset_name="bj_progress.svg",
            scanner=lambda: scan_two_levels(Path("백준")),
            show_total=True,
        ),
        PlatformConfig(
            name="Programmers",
            color="#2ecc71",
            asset_name="programmers_progress.svg",
            scanner=lambda: scan_two_levels(Path("프로그래머스")),
        ),
        PlatformConfig(
            name="SWEA",
            color="#3498db",
            asset_name="swea_progress.svg",
            scanner=lambda: scan_two_levels(Path("SWEA")),
        ),
        PlatformConfig(
            name="Codetree",
            color="#9b59b6",
            asset_name="codetree_progress.svg",
            scanner=scan_codetree,
        ),
        PlatformConfig(
            name="LeetCode",
            color="#e67e22",
            asset_name="leetcode_progress.svg",
            scanner=lambda: scan_one_level(Path("Leetcode")),
        ),
    ]


def compute_stats() -> tuple[list[PlatformStat], str | None]:
    baekjoon_profile = get_baekjoon_profile("hye0328")
    baekjoon_total = baekjoon_profile.get("solvedCount")
    baekjoon_tier = solved_ac_tier_name(baekjoon_profile.get("tier"))

    results: list[PlatformStat] = []

    for config in platform_configs():
        problems = config.scanner()
        solved_count = len(problems)
        total_count = baekjoon_total if config.show_total and isinstance(baekjoon_total, int) else solved_count

        generate_progress_svg(
            name=config.name,
            current=solved_count,
            total=total_count,
            color=config.color,
            filename=config.asset_name,
        )

        results.append(
            PlatformStat(
                name=config.name,
                solved_count=solved_count,
                total_count=total_count,
                last_commit=last_commit_from_folders(problems),
                show_total=config.show_total,
            )
        )

    return results, baekjoon_tier


def replace_block(text: str, start: str, end: str, content: str) -> str:
    if start not in text or end not in text:
        return text

    pattern = re.compile(f"{re.escape(start)}.*?{re.escape(end)}", re.DOTALL)
    return pattern.sub(f"{start}\n{content}\n{end}", text, count=1)


def replace_inline_block(text: str, start: str, end: str, content: str) -> str:
    if start not in text or end not in text:
        return text

    pattern = re.compile(f"{re.escape(start)}.*?{re.escape(end)}", re.DOTALL)
    return pattern.sub(f"{start} {content} {end}", text, count=1)


def update_readme(stats: list[PlatformStat], baekjoon_tier: str | None) -> None:
    readme = README_PATH.read_text(encoding="utf-8")

    stats_table = [
        "| Platform | Problems | Last Commit |",
        "|---|---:|---|",
    ]

    for platform in stats:
        stats_table.append(
            f"| {platform.name} | {platform.count_label} | {platform.last_commit} |"
        )

    total_problems = sum(platform.solved_count for platform in stats)

    badges_md = " ".join(
        f"![{platform.name}](https://img.shields.io/badge/{platform.name}-{platform.solved_count}-blue)"
        for platform in stats
    )

    language_stats = get_language_stats()
    language_md = "\n".join(
        f"- **{language}**: {percent}%"
        for language, percent in language_stats.items()
    )

    updated_at = datetime.now(KST).strftime("%Y-%m-%d %H:%M KST")

    readme = replace_block(
        readme,
        "<!-- STATS:START -->",
        "<!-- STATS:END -->",
        "\n".join(stats_table),
    )

    readme = replace_block(
        readme,
        "<!-- TOTAL:START -->",
        "<!-- TOTAL:END -->",
        f"**Total Problems:** {total_problems}",
    )

    readme = replace_block(
        readme,
        "<!-- BADGES:START -->",
        "<!-- BADGES:END -->",
        badges_md,
    )

    if language_md:
        readme = replace_block(
            readme,
            "<!-- LANG:START -->",
            "<!-- LANG:END -->",
            language_md,
        )

    if baekjoon_tier:
        readme = replace_inline_block(
            readme,
            "<!-- TIER:START -->",
            "<!-- TIER:END -->",
            f"**{baekjoon_tier}**",
        )

    readme = replace_block(
        readme,
        "<!-- UPDATED:START -->",
        "<!-- UPDATED:END -->",
        f"🕒 Last Auto Update: {updated_at}",
    )

    README_PATH.write_text(readme, encoding="utf-8")


def main() -> None:
    stats, baekjoon_tier = compute_stats()
    update_readme(stats, baekjoon_tier)


if __name__ == "__main__":
    main()
