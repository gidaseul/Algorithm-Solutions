import sys
input = sys.stdin.readline

N, M = map(int, input().split())

arr1 = []
for _ in range(N):
    row = list(map(int, input().split()))
    arr1.append(row)

arr2 = []
for _ in range(N):
    row = list(map(int, input().split()))
    arr2.append(row)

result = [[0 if arr1[i][j] == arr2[i][j] else 1 for j in range(M)] for i in range(N)]

for row in result:
    print(*row)