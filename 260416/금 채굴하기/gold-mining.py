n, m = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(n)]

total_gold = sum(grid[i][j] for i in range(n) for j in range(n))

# r,c가 주어질 때 K 범위 만큼의 마름모로 확인하는 과정
def dia_count(r,c,K):
    cnt = 0
    for i in range(r-K,r+K+1):
        col_range = K - abs(i-r)
        for j in range(c-col_range, c+col_range+1):
            if 0<= i<n and 0<=j<m:
                if grid[i][j] == 1:
                    cnt+=1

    return cnt

# 현 좌표 기준으로 최적 K 탐색
def check(r,c):
    max_cnt = 0

    for K in range(1,2*n+1):
        cost = K**2 + (K+1)**2

        if total_gold * m < cost:
            break
        
        cnt = dia_count(r,c,K)
        if cnt * m >= cost:
            max_cnt = max(max_cnt,cnt)
    return max_cnt

result = 0
for r in range(n):
    for c in range(n):
        result = max(result, check(r,c))

print(result)