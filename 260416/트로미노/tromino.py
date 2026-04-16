import math 

n, m = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(n)]



ans = 0

for i in range(n):
    for j in range(m):

        for d in range(4):
            l_shapes = [
                [(-1, 0), (0, 1)],  # 위, 오른쪽
                [(0, 1), (1, 0)],   # 오른쪽, 아래
                [(1, 0), (0, -1)],  # 아래, 왼쪽
                [(0, -1), (-1, 0)]  # 왼쪽, 위
            ]

            for shape in l_shapes:
                current = grid[i][j]
                if_possible = True
                for dr,dc in shape:
                    nr,nc = i+dr, j+dc
                    if 0 <= nr < n and 0 <=nc < m:
                        current == grid[nr][nc]
                    else:
                        is_possible = False
                        break
                if is_possible:
                    ans = max(ans,current)

            if j +2 < m:
                ans = max(ans, grid[i][j]+grid[i][j+1]+grid[i][j+2])
            if i +2 < n:
                ans = max(ans, grid[i][j]+grid[i+1][j]+grid[i+2][j])


print(ans)

