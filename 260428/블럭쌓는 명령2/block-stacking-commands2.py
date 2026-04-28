n, k = map(int, input().split())
commands = [tuple(map(int, input().split())) for _ in range(k)]

result = [0] * (n+1)
# Please write your code here.
for a,b in commands:
    for i in range(a,b+1):
        result[i] += 1

print(max(result))