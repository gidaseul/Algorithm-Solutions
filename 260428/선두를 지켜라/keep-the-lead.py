n, m = map(int, input().split())

a1 = [0]
for _ in range(n):
    vi, ti = map(int, input().split())
    for _ in range(ti):
        a1.append(a1[-1]+vi)

a2 = [0]
for _ in range(m):
    vi, ti = map(int, input().split())
    for _ in range(ti):
        a2.append(a2[-1]+vi)

max_len = max(len(a1), len(a2))
while len(a1) < max_len:
    a1.append(a1[-1])
while len(a2) < max_len:
    a2.append(a2[-1])

cur = None  # ← 문자열 "None" 아닌 진짜 None
cnt = 0

for i in range(1, max_len):
    if a1[i] > a2[i]:
        result = "A"
    elif a1[i] < a2[i]:
        result = "B"
    else:
        continue

    if cur is None:
        cur = result
    elif cur != result:
        cur = result
        cnt += 1

print(cnt)