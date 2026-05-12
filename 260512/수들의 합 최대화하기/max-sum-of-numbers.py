n = int(input())
grid = [list(map(int, input().split())) for _ in range(n)]

# Please write your code here.

visited = [[False] * n for _ in range(n)]
answer = []

def is_False(x,y):
    for i in range(n):
        for j in range(n):
            if j==y:
                visited[i][j] = False 

def is_True(x,y):
    for i in range(n):
        for j in range(n):
            if j==y:
                visited[i][j] = True
            else:
                continue


def choose(curr_num):

    if curr_num == n:
        max_result = sum(answer)
        return max_result

    max_result = 0
    
    for j in range(n):
        if visited[curr_num][j]:
                continue
        
            # 이때 행, 열 모두 처리해야 함. 
        is_True(curr_num,j)
        answer.append(grid[curr_num][j])

        max_result = max(choose(curr_num+1), max_result)
    
        is_False(curr_num,j)
        answer.pop()
    return max_result
        

result = choose(0)
print(result)