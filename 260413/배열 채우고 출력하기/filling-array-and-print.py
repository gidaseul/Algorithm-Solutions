arr = list(map(str,input().split()))

result = ''
size = len(arr)
for i in range(size):
    result+=arr[size-1-i]

print(result)