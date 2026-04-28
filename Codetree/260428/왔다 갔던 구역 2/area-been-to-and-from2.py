# 선분을 배열로 표현

OFFSET = 1000

n = int(input())
x = []
dir = []
for _ in range(n):
    xi, di = input().split()
    x.append(int(xi))
    dir.append(di)

# Please write your code here.

result = [0 for _ in range(2*OFFSET+1)]

pos = OFFSET
for i in range(n):
    if dir[i] == 'R':
        for _ in range(x[i]):
            pos += 1 
            result[pos] += 1

    if dir[i] == 'L':
        for _ in range(x[i]):
            result[pos] += 1
            pos -= 1

res = 0
for i in range(2*OFFSET+1):
    if result[i] >= 2:
        res += 1

print(res)