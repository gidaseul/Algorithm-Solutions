import sys
input = sys.stdin.readline

def solve():
    n, m = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(n)]

    # 1. 누적합
    prefix = [[0] * (m + 1) for _ in range(n + 1)]
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            prefix[i][j] = (arr[i-1][j-1]
                          + prefix[i-1][j]
                          + prefix[i][j-1]
                          - prefix[i-1][j-1])

    # 2. 직사각형 합
    def rect_sum(r1, c1, r2, c2):
        return (prefix[r2][c2]
              - prefix[r1-1][c2]
              - prefix[r2][c1-1]
              + prefix[r1-1][c1-1])

    # 3. 특정 열 범위에서 최대 직사각형 합
    def max_rect_col(c_start, c_end):
        best = float('-inf')
        for r1 in range(1, n + 1):
            for r2 in range(r1, n + 1):
                for c1 in range(c_start, c_end + 1):
                    for c2 in range(c1, c_end + 1):
                        best = max(best, rect_sum(r1, c1, r2, c2))
        return best

    # 4. 특정 행 범위에서 최대 직사각형 합
    def max_rect_row(r_start, r_end):
        best = float('-inf')
        for r1 in range(r_start, r_end + 1):
            for r2 in range(r1, r_end + 1):
                for c1 in range(1, m + 1):
                    for c2 in range(c1, m + 1):
                        best = max(best, rect_sum(r1, c1, r2, c2))
        return best

    # 5. 열 기준 left/right
    left = [float('-inf')] * (m + 2)
    right = [float('-inf')] * (m + 2)
    for k in range(1, m + 1):
        left[k] = max(left[k-1], max_rect_col(1, k))
    for k in range(m, 0, -1):
        right[k] = max(right[k+1], max_rect_col(k, m))

    # 6. 행 기준 top/bottom
    top = [float('-inf')] * (n + 2)
    bottom = [float('-inf')] * (n + 2)
    for k in range(1, n + 1):
        top[k] = max(top[k-1], max_rect_row(1, k))
    for k in range(n, 0, -1):
        bottom[k] = max(bottom[k+1], max_rect_row(k, n))

    # 7. 최종 답
    ans = float('-inf')
    for k in range(1, m):
        ans = max(ans, left[k] + right[k+1])
    for k in range(1, n):
        ans = max(ans, top[k] + bottom[k+1])

    print(ans)

solve()