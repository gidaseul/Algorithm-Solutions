OFFSET = 100
LENGTH = OFFSET * 2 + 1

n = int(input())
arr = [[0] * LENGTH for _ in range(LENGTH)]

for _ in range(n):
    # x1, y1 (좌측 하단), x2, y2 (우측 상단) 가정
    x1, y1, x2, y2 = map(int, input().split())
    
    # 좌표 정렬 (x1 < x2, y1 < y2 보장)
    start_x, end_x = min(x1, x2) + OFFSET, max(x1, x2) + OFFSET
    start_y, end_y = min(y1, y2) + OFFSET, max(y1, y2) + OFFSET

    # 실제 좌표 범위만큼 배열에 마킹
    for i in range(start_x, end_x):
        for j in range(start_y, end_y):
            arr[i][j] = 1

# 면적 계산
count = sum(row.count(1) for row in arr)
print(count)