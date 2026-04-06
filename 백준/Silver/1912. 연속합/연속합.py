import math

n = int(input())
arr = list(map(int,input().split()))

cur = arr[0]
ans = arr[0]

for i in range(1,n):
    cur = max(arr[i], arr[i]+cur)
    ans = max(cur,ans)

print(ans)