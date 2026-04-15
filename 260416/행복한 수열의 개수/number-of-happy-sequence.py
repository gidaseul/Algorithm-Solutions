n, m = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(n)]

count = 0

for i in range(n):
    
    # 가로 열 마다 m을 넘는지
    cnt = 0
    for j in range(n-1):

        if grid[i][j] == grid[i][j+1]:
            cnt +=1
        else:
            cnt = 1
        
        if cnt >= m:
            count+=1
            break

    cntK = 0
    for k in range(n-1):
        if grid[k][i] == grid[k+1][i]:
            cntK+=1
        else:
            cntK = 1
        if cntK>=m:
            count+=1
            break
print(count)

    
