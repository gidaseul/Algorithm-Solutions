N = int(input())

arr = list(map(int, input().split()))
result = [x **2 for x in arr]

print(*result)
