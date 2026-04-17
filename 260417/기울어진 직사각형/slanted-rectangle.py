n = int(input())
grid = [list(map(int, input().split())) for _ in range(n)]

# Please write your code here.
# 각 화살표 넘어가는 방향점을 기준으로 확인해야 함.(늘어나기 때문)
dx = [-1,-1,1,1] # ↗ ↖ ↙ ↘ 행방향 
dy =[1,-1,-1,1] # ↗ ↖ ↙ ↘ 열방향

final_result = 0
for r in range(n):
    for c in range(n):

        # 대각선 길이가 못해도 1부터 시작해야 하기 때문
        for a in range(1,n):
            for b in range(1,n):
                step = [a,b,a,b] # 대칭된다는 점을 이용해서 a,b를 이용해서 풀기
                cur_c, cur_r = r, c
                total = grid[r][c]
                check = True
                
                for i in range(4):
                    for _ in range(step[i]):
                        cur_c += dx[i]
                        cur_r += dy[i]

                        if 0 <= cur_r < n and 0 <= cur_c < n:
                            total += grid[cur_r][cur_c]
                        else:
                            check = False
                            break
                        
                    if not check:
                        break
                if check:
                    final_result = max(final_result, total-grid[r][c])
                            
print(final_result)