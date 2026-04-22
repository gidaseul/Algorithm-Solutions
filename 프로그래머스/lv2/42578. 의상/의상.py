from collections import defaultdict

def solution(clothes):
    dict_clothes = defaultdict(list)
    
    for name,kind in clothes:
        dict_clothes[kind].append(name)
    
    answer = 1
    for kind in dict_clothes:
        answer *= len(dict_clothes[kind]) + 1
    return answer -1