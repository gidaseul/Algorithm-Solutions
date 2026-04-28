n, m = map(int, input().split())

# Process A's movements
a1 = [0]
for _ in range(n):
    vi, ti = map(int, input().split())
    for _ in range(ti):
        a1.append(a1[-1]+v1)

# Process B's movements
a2 = [0]
for _ in range(m):
    vi, ti = map(int, input().split())
    for _ in range(ti):
        a2.append(a2[-1]+v1)

# Please write your code here.

cur = "A"
cnt = 0
for i in range(1,len(a1)):

    if a1[i] > a2[i]:
        result = "A"
        
        if cur == result:
            continue
        else:
            cur = "A"
            cnt +=1

    elif a1[i] < a2[i]:
        result = "B"

        if cur == result:
            continue
        else:
            cur = "B"
            cnt +=1
    else:
        continue
        
print(cnt)
