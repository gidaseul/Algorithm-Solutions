import sys
input = sys.stdin.readline

T=int(input())

for i in range(T):
  h,w,n=list(map(int,input().split()))
  floor=n%h
  roomNumber=(n//h)+1
  if floor==0:
    floor=h
    roomNumber=n//h
  
  print(floor*100+roomNumber)
  
  