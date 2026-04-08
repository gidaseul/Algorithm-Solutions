import sys
from collections import deque

# sys.stdin.readline을 짧은 변수명으로 설정
input = sys.stdin.readline

n,m = map(int,input().split())

graph = []
for _ in range(n):
    graph.append(list(map(int,input().rstrip()))) # rstrip 안하면 개행 문자 포함이여서 꼭 하기

x,y = 1,1

# 상하좌우
dx = [-1,1,0,0]
dy = [0,0,-1,1]

def BFS(x,y):
    queue = deque([(x,y)])
    while queue:
        x,y = queue.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or ny <0 or nx >= n or ny >= m:
                continue
            if graph[nx][ny] == 0:
                continue
            if graph[nx][ny] == 1:
                graph[nx][ny] = graph[x][y] + 1
                queue.append((nx,ny))
        
    return graph[n-1][m-1] # 마지막 그래프까지 어떻게 도달했는지 거리 반환을 의미함.

# 실제 위치는 1,1이지만 그래프 인덱스 기준으로는 0,0!
print(BFS(0,0))
