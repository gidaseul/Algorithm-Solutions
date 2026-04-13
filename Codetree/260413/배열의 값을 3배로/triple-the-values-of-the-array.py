arr = []
for _ in range(3):
    row = list(map(int, input().split()))
    arr.append(row)

# 3배로 만들기
result = [[x * 3 for x in row] for row in arr]

# 출력
for row in result:
    print(*row)