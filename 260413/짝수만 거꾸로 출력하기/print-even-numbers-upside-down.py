N = int(input())

arr = list(map(int,input().split()))

result = []

for a in arr:
    if a%2==0:
        result.append(a)

print(*result[::-1])
    