n = int(input())
arr = list(map(int, input().split()))

# Please write your code here.

result = [arr[0]]

for i in range(3,n+1):
    if i%2 != 0:
        sum = []
        for j in range(i):
            sum.append(arr[j])
        
        sum.sort()
        idx = len(sum) //2
        result.append(sum[idx])
    else:
        continue
print(*result)