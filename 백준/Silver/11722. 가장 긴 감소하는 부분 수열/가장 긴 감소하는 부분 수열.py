import sys


A = int(input())

arr = list(map(int,input().split()))    

result = [0] * (A+1)
for i in range(A):
    result[i] = 1
    for j in range(i):
        if(arr[j] > arr[i]):
            result[i] = max(result[j]+1, result[i])

print(max(result))

