n = int(input())
costs = [list(map(int, input().split())) for _ in range(n)]

visited = [False] * n
answer = float("inf")

def backtrack(current, count, total_cost):
    global answer

    # 모든 도시를 방문한 경우
    if count == n:
        # 현재 도시에서 1번 도시로 돌아가는 비용 추가
        if costs[current][0] != 0:
            answer = min(answer, total_cost + costs[current][0])
        return

    # 가지치기: 이미 현재 최소 비용보다 크면 볼 필요 없음
    if total_cost >= answer:
        return

    for next_city in range(n):
        # 이미 방문한 도시는 제외
        if visited[next_city]:
            continue

        # 이동 불가능한 경로라면 제외
        if costs[current][next_city] == 0:
            continue

        visited[next_city] = True
        backtrack(
            next_city,
            count + 1,
            total_cost + costs[current][next_city]
        )
        visited[next_city] = False


# 1번 도시에서 출발
visited[0] = True
backtrack(0, 1, 0)

print(answer)