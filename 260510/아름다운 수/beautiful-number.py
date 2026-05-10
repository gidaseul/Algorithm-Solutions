N = int(input())
answer = 0

def dfs(length):
    global answer

    if length == N:
        answer+=1
        return

    if length > N:
        return

    for num in range(1,5):
        dfs(length+num)

dfs(0)
print(answer)