import math

def solution(progresses, speeds):
    answer = []
    day = []
    
    for i in range(len(progresses)):
        remain = 100 - progresses[i]
        day.append(math.ceil(remain/speeds[i])) # 올림 연산
        
    count = 1
    current_max = day[0]
    
    for i in range(1, len(day)):
        if day[i] > current_max:
            answer.append(count)
            count = 1
            current_max = day[i]
        else:
            count += 1
            
    answer.append(count)
    return answer