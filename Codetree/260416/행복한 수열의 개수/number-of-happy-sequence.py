def is_happy(line, m):
    cnt = 1
    for i in range(1, len(line)):
        if line[i] == line[i - 1]:
            cnt += 1
        else:
            cnt = 1

        if cnt >= m:
            return True

    return m == 1


n, m = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(n)]

count = 0

for i in range(n):
    row = grid[i]
    col = [grid[j][i] for j in range(n)]

    if is_happy(row, m):
        count += 1
    if is_happy(col, m):
        count += 1

print(count)