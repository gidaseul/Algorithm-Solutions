ans = 0
for _ in range(4):
    arr = list(map(int,input().split()))
    for i in range(4):
        if arr[i] % 5 ==0:
            ans+=1

print(ans)
