n = int(input())  # 분해합을 입력값으로 받음
result=0
for i in range(1,n+1):
  a=list(map(int,str(i)))
  result=i+sum(a)
  if n==result:
    print(i)
    break
  if i==n:
    print(0)