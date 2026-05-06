N = int(input())
A = list(map(int, input().split()))

# Please write your code here.
n = len(A)
cnt = 0
for i in range(n-2):
    a1 = A[i]
    for j in range(i+1,n-1):
        a2 = A[j]
        if a1 <= a2:
            for k in range(j+1,n):
                a3 = A[k]
                if a2<=a3:
                    cnt+=1
        else:
            continue

print(cnt)