n = int(input())
grid = [list(map(int, input().split())) for _ in range(n)]

# Please write your code here.

visited = [False] * (n+1)

result = []
answer = 0 
def choose(curr_idx):
    global answer

    if curr_idx == n:
        min_result = min(result)
        answer = max(min_result,answer)
        return answer

    
    for i in range(n):
        if visited[i]:
            continue

        visited[i] = True
        result.append(grid[curr_idx][i])
        choose(curr_idx+1)
        result.pop()
        visited[i] = False

    return

choose(0)
print(answer)