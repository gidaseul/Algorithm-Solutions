n,m =map(int,input().split())
ary=[i for i in range(1,n+1)]

for i in range(m):
    i,j=map(int,input().split())
    ary[i-1],ary[j-1] = ary[j-1], ary[i-1]
print(" ".join(map(str,ary)))