n = int(input())

arr = [[0] * n for _ in range(n)]

for i in range(1,n+1):
    
    if i % 2==0:
        for r in range(n):
            c = i-1
            arr[n-r-1][c] = r+1
    else:
        for r in range(n):
            c = i-1
            arr[r][c] = r+1

result = '\n'.join([''.join(map(str,row)) for row in arr])
print(result)