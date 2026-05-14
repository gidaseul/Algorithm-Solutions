n = int(input())
grid = [list(map(int, input().split())) for _ in range(n)]

# Please write your code here.
bomb_positions = []

for i in range(n):
    for j in range(n):
        if grid[i][j] == 1:
            bomb_positions.append((i, j))

bomb_shape = {
    1: [(-2,0),(-1,0),(0,0),(1,0),(2,0)],
    2: [(-1,0),(0,0),(0,-1),(1,0),(0,1)],
    3: [(-1,-1),(-1,1),(0,0),(1,-1),(1,1)]
}

selected = []

def count_exploded():
    exploded_map = [[False] * n for _ in range(n)]

    for idx in range(len(bomb_positions)):

        x, y = bomb_positions[idx]
        bomb_type = selected[idx]

        for dx, dy in bomb_shape[bomb_type]:
            nx = x+dx
            ny = y+dy

            if 0<=nx<n and 0<=ny<n:
                exploded_map[nx][ny] = True
        
    count = 0
    for i in range(n):
        for j in range(n):

            if exploded_map[i][j]:
                count+=1

    return count

def backtracking(depth: int) -> int:
    
    if depth == len(bomb_positions):
        return count_exploded()   # 직접 반환

    best = 0

    for i in range(1, 4):
        selected.append(i)
        result = backtracking(depth + 1)  # 반환값을 받음
        best = max(best, result)          # 누적
        selected.pop()

    return best   # 상위 호출자에게 전달


answer = backtracking(0)   # global 변수 불필요
print(answer)