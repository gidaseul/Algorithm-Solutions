arr = [list(map(int, input().split())) for _ in range(4) ]

ans = 0

for i in range(4):
    for j in range(i+1):
        ans += arr[i][j]

print(ans)
        