def factorial(n):
  a=1
  for i in range(2,n+1):
    a*=i
  return a

def binomial_coefficient(n,r):
  return factorial(n) // factorial(n-r) // factorial(r) 

n,r=map(int,input().split())
print(binomial_coefficient(n,r))