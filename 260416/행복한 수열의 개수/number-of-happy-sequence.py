n, m = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(n)]

count = 0

for i in range(n):
    
    # 가로 열 마다 m을 넘는지
    cnt = 0
    for j in range(n):
        if j == 0:
            temp = grid[i][j]
            cnt = 1
            if cnt >=m:
                count+=1
                break
        else:
            if temp == grid[i][j]:
                cnt+=1
            else:
                temp = grid[i][j]
                cnt =1

            if cnt>=m:
                count+=1
                break


    cntK = 0
    for k in range(n):
        if j == 0:
            temp = grid[k][i]
            cntK = 1
            if cntK >=m:
                count+=1
                break
        else:
            if temp == grid[k][i]:
                cntK+=1
            else:
                temp = grid[k][i]
                cntK =1

            if cntK>=m:
                count+=1
                break

print(count)

    
