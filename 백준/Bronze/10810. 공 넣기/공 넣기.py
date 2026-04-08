n,m =map(int,input().split())
ary=[0]*n
for i in range(m):
    i,j,k=map(int,input().split())
    for a in range(i,j+1):
        ary[a-1]=k
print(" ".join(map(str,ary)))