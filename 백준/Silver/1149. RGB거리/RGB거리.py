import sys
import math
a = int(input())
arr = [list(map(int, input().split())) for _ in range(a)]

result = [[0] * 3 for _ in range(a)]

for i in range(a):
    if i == 0:
        result[i][0] = arr[i][0]
        result[i][1] = arr[i][1] 
        result[i][2] = arr[i][2] 
    else:
        result[i][0] = min(result[i-1][1], result[i-1][2]) + arr[i][0]
        result[i][1] = min(result[i-1][0], result[i-1][2]) + arr[i][1] 
        result[i][2] = min(result[i-1][0], result[i-1][1]) + arr[i][2] 
    


minResult = min(result[a-1][0],result[a-1][1],result[a-1][2])
print(minResult)
