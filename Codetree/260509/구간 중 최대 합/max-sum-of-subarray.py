n, k = map(int, input().split())
arr = list(map(int, input().split()))

# Please write your code here.

result = 0
for i in range(n-k+1):
    ans = 0
    for j in range(k):
        ans += arr[i+j]
    result = max(ans, result)
print(result)