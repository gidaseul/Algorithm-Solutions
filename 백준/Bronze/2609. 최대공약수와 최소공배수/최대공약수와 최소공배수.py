def gcd(a, b):
  r=0
  while (b!=0):
    r=a%b
    a=b
    b=r
  return a

def lcm(a,b):
  return (a*b)/gcd(a,b)

one,two=map(int, input().split())
minNum=int(gcd(one,two))
maxNum=int(lcm(one,two))

print(minNum,maxNum)