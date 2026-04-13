import sys
input = sys.stdin.readline

arr1 = []
arr2 = []

for _ in range(3):
    row = list(map(int, input().split()))
    arr1.append(row)

input()  # 빈 줄 건너뛰기

for _ in range(3):
    row = list(map(int, input().split()))
    arr2.append(row)

result = [[arr1[i][j] * arr2[i][j] for j in range(3)] for i in range(3)]

for row in result:
    print(*row)