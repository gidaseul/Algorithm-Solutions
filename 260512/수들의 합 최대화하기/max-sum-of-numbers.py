n = int(input())
grid = [list(map(int, input().split())) for _ in range(n)]

# Please write your code here.

visited = [False] * (n+1)

answer = []

def choose(curr_num):

    if curr_num == n:
        max_result = sum(answer)
        return max_result

    max_result = 0

    for i in range(n):
        if visited[i]:
            continue
        
        visited[i] = True
        answer.append(grid[curr_num][i])
        max_result = max(max_result, choose(curr_num+1))

        visited[i] = False
        answer.pop()

    return max_result

result = choose(0)
print(result)
