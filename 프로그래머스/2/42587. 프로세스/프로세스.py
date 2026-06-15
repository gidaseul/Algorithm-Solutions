from collections import deque

def solution(priorities, location):
    queue = deque()

    for idx, priority in enumerate(priorities):
        queue.append((idx,priority))

    answer = 0

    while queue:
        cur_idx , cur_result = queue.popleft()

        has_higher = False

        for _, priority in queue:
            if priority > cur_result:
                has_higher = True
                break

        if has_higher:
            queue.append((cur_idx,cur_result))

        else:
            answer+=1

            if cur_idx == location:
                return answer