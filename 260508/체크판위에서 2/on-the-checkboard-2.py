R, C = map(int, input().split())
grid = [list(input().split()) for _ in range(R)]
answer = 0

for r1 in range(1, R - 1):
    for c1 in range(1, C - 1):

        # 시작점 -> 첫 번째 중간점 색이 달라야 함
        if grid[0][0] == grid[r1][c1]:
            continue
        
        for r2 in range(r1 + 1, R - 1):
            for c2 in range(c1 + 1, C - 1):
                # 첫 번째 중간점 -> 두 번째 중간점 색이 달라야 함
                if grid[r1][c1] == grid[r2][c2]:
                    continue
                # 두 번째 중간점 -> 도착점 색이 달라야 함
                if grid[r2][c2] == grid[R - 1][C - 1]:
                    continue
                answer += 1

print(answer)