n,m =map(int,input().split())
ary=[i for i in range(1,n+1)]

for i in range(m):
    i,j=map(int,input().split())
    array=ary[i-1:j]
    array.reverse()
    ary[i-1:j]=array

print(" ".join(map(str,ary)))