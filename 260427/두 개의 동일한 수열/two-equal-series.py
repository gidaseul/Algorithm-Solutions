n = int(input())
A = list(map(int, input().split()))
B = list(map(int, input().split()))

# Please write your code here.
A.sort()
B.sort()
num = 0
for i in A:
    
    if num+1 == len(A):
        print("Yes")

    if B[num] == i:
        num+=1

    else:
        print("No")
        break

