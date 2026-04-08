from itertools import combinations

n, m = map(int, input().split())
arr = list(map(int, input().split()))
res = 0

for num in combinations(arr, 3):
    tmp_sum = sum(num)
    if res < tmp_sum <= m:
        res = tmp_sum
print (res)