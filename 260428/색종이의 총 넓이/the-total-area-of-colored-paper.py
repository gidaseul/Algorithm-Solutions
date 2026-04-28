OFFSET = 100
LENGTH = OFFSET * 2 +1


n = int(input())
points = [tuple(map(int, input().split())) for _ in range(n)]
x, y = zip(*points)
x, y = list(x), list(y)

# Please write your code here.

arr = [[0 for _ in range(LENGTH)] for _ in range(LENGTH)]

for i in range(n):
    startX = OFFSET + x[i]
    startY = OFFSET + y[i]
    for r in range(8):
        for c in range(8):
            arr[startX+r][startY+c] 
cnt = 0
for i in range(LENGTH):
    for j in range(LENGTH):
        if arr[i][j] > 0:
            cnt +=1

print(cnt)