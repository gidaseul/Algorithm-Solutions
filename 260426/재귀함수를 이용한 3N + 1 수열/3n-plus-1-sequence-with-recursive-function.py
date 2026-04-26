n = int(input())

# Please write your code here.

def fun(n,c):
    if n==1:
        return c

    if n%2 ==0:
        return fun(n//2,c+1)
    else:
        return fun(n*3+1,c+1)

print(fun(n,0))