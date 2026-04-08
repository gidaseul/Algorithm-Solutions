INF=int(1e9)

n=int(input())
m=int(input())

array=[[INF] * (n+1) for _ in range(n+1)]

for i in range(1, n+1):
    array[i][i]=0


for _ in range(m): 
  a,b,c = map(int,input().split())
  if array[a][b] > c:
    array[a][b]=c

for k in range(1,n+1):
  for i in range(1,n+1):
    for j in range(1,n+1):
      array[i][j] = min(array[i][j], array[i][k]+array[k][j])

for i in range(1, n+1):
  for j in range(1, n+1):
    if array[i][j]==INF:
      print("0",end=" ")
    else:
      print(array[i][j],end=" ")
  print()

