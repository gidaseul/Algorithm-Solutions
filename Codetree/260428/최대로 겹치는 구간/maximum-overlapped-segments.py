OFFSET = 100

n = int(input())
commands = [tuple(map(int, input().split())) for _ in range(n)]

result = [0] * 201

# Please write your code here.
for a,b in commands:
    for i in range(a,b):
        result[i+OFFSET] += 1

print(max(result))
    