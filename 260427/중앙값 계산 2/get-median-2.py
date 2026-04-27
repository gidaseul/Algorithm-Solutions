n = int(input())
arr = list(map(int, input().split()))

# Please write your code here.
arr.sort()

num = n//2
result = []
for i in range(num+1):
    result.append(arr[i])

print(*result)