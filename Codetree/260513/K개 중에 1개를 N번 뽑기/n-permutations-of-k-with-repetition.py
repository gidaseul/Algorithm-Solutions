K, N = map(int, input().split())

# Please write your code here.

result = []
def choose(curr_idx):

    if curr_idx == N+1:
        print(*result)
        return

    for i in range(1,K+1):

        result.append(i)
        choose(curr_idx+1)
        result.pop()
        

    return
choose(1)
