import sys
input = sys.stdin.readline

n = int(input())
s = [0] * (n + 1)
for i in range(1, n + 1):
    s[i] = int(input())

# dp[i][0] = 다음 칸은 반드시 건너뛰어야 하는 점(이미 연속 2번 값 저장된 상태)
# dp[i][1] = i번째 계단이 "첫 번째 연속" (i-1 안 밟음) - 이전 값 무시하고 두번째 값 저장
dp = [[0] * 2 for _ in range(n + 1)]

if n >= 1:
    dp[1][0] = 0        # i-1(0번)을 밟을 수 없으므로 불가 → 0으로 둠
    dp[1][1] = s[1]     # 1번만 밟기



if n >= 2:
    dp[2][0] = s[1] + s[2]  # 1, 2 연속 (이전 값을 저장한 상태)
    dp[2][1] = s[2]          # 2만 (1 건너뜀)

for i in range(3, n + 1):
    # i번이 두 번째 연속 → i-1은 첫 번째 연속이어야 함
    dp[i][0] = dp[i-1][1] + s[i]
    # i번이 첫 번째 연속 → i-1은 안 밟음, i-2는 뭐든 가능
    dp[i][1] = max(dp[i-2][0], dp[i-2][1]) + s[i]

print(max(dp[n][0], dp[n][1]))