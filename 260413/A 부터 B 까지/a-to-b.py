A,B = map(int,input().split())

nums = []

while A <= B:
    nums.append(A)
    if A % 2==0:
        A+=3
    else:
        A*=2
print(*nums)
