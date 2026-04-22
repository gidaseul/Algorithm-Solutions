from collections import Counter

def solution(participant, completion):
    p = Counter(participant)
    c = Counter(completion)
    
    result = p-c
    answer = list(result.keys())[0]
    return answer
