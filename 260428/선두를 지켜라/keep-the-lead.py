n, m = map(int, input().split())

# Process A's movements
a1 = [0]
for _ in range(n):
    vi, ti = map(int, input().split())
    result = a1[-1] + vi
    for _ in range(ti):
        a1.append(result)

# Process B's movements
a2 = [0]
for _ in range(m):
    vi, ti = map(int, input().split())
    result = a2[-1]+vi
    for _ in range(ti):
        a2.append(result)

# Please write your code here.
cnt = 1
check = True
for i in range(len(a1)):

    if a1[i] > a2[i] and check:
        check = False
        cnt+=1
    elif a1[i] < a2[i] and not check:
        check = True
        cnt+=1

print(cnt) 
        
    