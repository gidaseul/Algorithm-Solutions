A, B = map(int,input().split())

result = 0
for i in range(A,B+1):
    if i % 2==0:
        result += i

print(result)