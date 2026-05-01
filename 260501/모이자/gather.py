import sys

n = int(input())
arr = list(map(int, input().split()))

# Please write your code here.

result = sys.maxsize
for i in range(n):
    sum = 0
    for j in range(n):

        len = abs(i-j)
        sum += (arr[j] * len)
    
    result = min(sum ,result)

print(result)