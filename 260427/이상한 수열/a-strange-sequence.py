N = int(input())

# Please write your code here.
def fun(n):

    if n == 1:
        return 1
    if n == 2:
        retrun 2
    return fun(n//3) + fun(n-1)


print(fun(N))