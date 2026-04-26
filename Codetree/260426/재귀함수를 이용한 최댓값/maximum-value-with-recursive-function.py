n = int(input())
arr = list(map(int, input().split()))

# Please write your code here.
def func(a):
    if a==0:
        return arr[0]
    
    return max(func(a-1),arr[a])

print(func(n-1))