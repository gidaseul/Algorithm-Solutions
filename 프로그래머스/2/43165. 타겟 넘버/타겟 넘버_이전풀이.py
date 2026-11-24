from collections import deque

def solution(numbers, target):
    answer = 0
    queue = deque()
    n = len(numbers)
    queue.append([numbers[0],0]) # +인 상태로 넣기
    queue.append([-1*numbers[0],0]) # -인 상태로 넣기
    while queue:
        temp, idx = queue.popleft()  # 나눠 가지기
        idx += 1
        if idx < n:
            queue.append([temp+numbers[idx], idx])
            queue.append([temp-numbers[idx], idx])
        else:
            if temp == target:
                answer += 1
    return answer
