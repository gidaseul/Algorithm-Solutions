import sys
input = sys.stdin.readline
INF = sys.maxsize

d=[]
n=int(input())
for _ in range(n):
  r,c=map(int,input().split())
  d.append((r,c))

array=[[INF] * n for _ in range(n)]
for i in range(n):
  array[i][i]=0

for diagonal in range(1,n):
  for i in range(0,n-diagonal):
    j=i+diagonal
    if diagonal ==1: #대각선이 하나일떄는 그냥 단순히 계산 값이 행렬연산이기 때문에
      array[i][j] = d[i][0]*d[j][0]*d[j][1]
      continue
    
    for k in range(i,j):
      array[i][j]=min(array[i][j],array[i][k]+array[k+1][j]+d[i][0]*d[k][1]*d[j][1])

print(array[0][n-1])