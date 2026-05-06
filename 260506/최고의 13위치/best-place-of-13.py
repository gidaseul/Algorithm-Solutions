n = int(input())
grid = [list(map(int, input().split())) for _ in range(n)]


ans = 0

for i in range(n):
    result = 0
    
    for j in range(n-2):
        result = grid[i][j] + grid[i][j+1] + grid[i][j+2]
        ans = max(result, ans)
    
    if ans == 3:
        break

print(ans)