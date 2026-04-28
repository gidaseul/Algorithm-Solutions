OFFSET =1000
LENGTH = 2 * OFFSET + 1

grid = [[0 for _ in range(LENGTH)] for _ in range(LENGTH)]

def convert(v):
    return int(v) + OFFSET

for i in range(1,4):
    x1, y1, x2, y2 = map(convert, input().split())

    for y in range(y1,y2):
        for x in range(x1,x2):
            grid[y][x] = i

cnt = 0

for y in range(LENGTH):
        for x in range(LENGTH):
            if grid[y][x] in [1,]:
                cnt+=1
print(cnt)