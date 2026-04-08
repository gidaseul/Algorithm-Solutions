import sys

N, K = map(int, input().split())

arr = []

for _ in range(N):
    arr.append(list(map(int,input().split())))



dp = [[0] * (K+1)  for _ in range(N+1)]

for i in range(1,N+1):
    weight, value = arr[i-1]
    for w in range(K+1):
        dp[i][w] = dp[i-1][w]
        if weight <= w:
            dp[i][w] = max(dp[i-1][w], dp[i-1][w-weight]+value)

print(dp[N][K])

