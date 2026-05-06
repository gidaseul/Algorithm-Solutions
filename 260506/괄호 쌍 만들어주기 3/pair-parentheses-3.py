A = input()

# Please write your code here.
cnt = 0
for i in range(len(A)-1):
    start = A[i]
    if start == "(":
        for j in range(i+1,len(A)):     
            if A[j] == ")":
                cnt +=1
print(cnt)
        
