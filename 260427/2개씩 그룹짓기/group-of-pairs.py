import math 

n = int(input())
nums = list(map(int, input().split()))

# Please write your code here.
nums.sort()
ans = 0
for i in range(n):
    idx = 1+i
    result = nums[i] + nums[-idx]
    ans = max(ans, result)

print(ans)
