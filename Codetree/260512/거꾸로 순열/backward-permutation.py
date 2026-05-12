n = int(input())
# Please write your code here.
visited = [False] * (n+1)

answer = []

def print_def():
    for ans in answer:
        print(ans, end=" ")
    print()

def choose(curr_num):

    if curr_num == n+1:
        print_def()
        return

    for i in range(n,0,-1):
        if visited[i]:
            continue
        
        visited[i] = True
        answer.append(i)
        choose(curr_num+1)
        answer.pop()
        visited[i] = False


choose(1)