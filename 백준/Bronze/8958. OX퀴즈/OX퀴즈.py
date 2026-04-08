import sys
input = sys.stdin.readline

num=int(input())
for i in range(num):
  str=input()
  count=0
  sum=0
  for j in str:
    if j=='O':
      count+=1
    else:
      count=0
    sum+=count
  print(sum,end=" ")

