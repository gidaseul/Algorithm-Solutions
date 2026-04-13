
N = int(input())
for i in range(N):
    a,b = map(int,input().split())
    result = 0
    for j in range(a,b+1):
        result+=j
        print(result)
    


