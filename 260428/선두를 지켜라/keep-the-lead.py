n, m = map(int, input().split())

# Process A's movements
a1 = [0]
for _ in range(n):
    vi, ti = map(int, input().split())
    for _ in range(ti):
        a1.append(a1[-1]+vi)

# Process B's movements
a2 = [0]
for _ in range(m):
    vi, ti = map(int, input().split())
    for _ in range(ti):
        a2.append(a2[-1]+vi)

# Please write your code here.

cur = "None"
cnt = 0
for i in range(1,len(a1)):

    if a1[i] > a2[i]:
        result = "A"
    elif a1[i] < a2[i]:
        result = "B"
    else:
        continue

    if cur is None:       # 첫 선두 결정은 카운트 안 함
        cur = result
    elif cur != result:   # 선두가 바뀔 때만 카운트
        cur = result
        cnt += 1



print(cnt)
