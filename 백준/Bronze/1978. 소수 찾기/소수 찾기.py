n=int(input())
number=list(map(int,input().split()))
t=0
for i in number:
  if i==1:
    continue

  for a in range(2,i):
    if(i%a==0):
      break
  else:
    t+=1

print(t)