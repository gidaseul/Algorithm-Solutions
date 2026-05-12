n = int(input())

# Please write your code here.
visited = [False] * (n+1)
answer = []

def print_answer():
    for elem in answer:
        print(elem, end=" ")
    print()

def choose(curr_num):
    # 순열로 만들고자 하는 수의 +1일 때 멈춰야 함.
    if curr_num == n+1:
        print_answer()
        return

    for i in range(1, n+1):
        if visited[i]:
            continue
        
        visited[i] = True

        answer.append(i)

        choose(curr_num+1)

        answer.pop()
        
        visited[i] = False

choose(1)