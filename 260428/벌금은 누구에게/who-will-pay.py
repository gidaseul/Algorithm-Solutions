# Please write your code here.
N, M, K = map(int, input().split())
student = [int(input()) for _ in range(M)]

# Please write your code here.
arr = [0] * (M+1)
for i in student:
    arr[i] += 1

    if arr[i] >= K:
        print(i)
        exit()
    
print(-1)


