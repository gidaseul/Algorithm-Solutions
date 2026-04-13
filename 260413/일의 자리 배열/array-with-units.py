arr = list(map(int, input().split()))


for i in range(2,10):
    result = (arr[i-1]+arr[i-2]) % 10
    arr.append(result)

print(*arr)