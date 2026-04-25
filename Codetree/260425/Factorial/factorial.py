N = int(input())

# Please write your code here.

def factorial(N):
    if N == 0 or N== 1:
        return 1

    if N == 2:
        return 2

    sum = 1
    for i in range(1,N+1):
        sum *= i
    
    return sum

print(factorial(N))