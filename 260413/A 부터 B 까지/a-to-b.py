A,B = map(int,input().split())
arr = []
for i in range(A,B+1):
    if i %2==0:
        arr.append(i*3)
    else:
        arr.append(i*2)

print(*arr)
