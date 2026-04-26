N = int(input())

# Please write your code here.

def check(n):
    if n <= 0:
        return 0

    if n % 2 ==0:
        return check(n-2) + n
    else:
        return check(n-2) + n 

print(check(N))
