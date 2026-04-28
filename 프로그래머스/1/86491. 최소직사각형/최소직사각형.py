import sys, math, copy


"""
결국엔 한 쪽으로 큰 길이, 작은 길이로 정해져서 푸는 게 효과적임
"""

def solution(sizes):
    
    result = 0
    for i in range(len(sizes)):
        
        w, h = sizes[i][0], sizes[i][1]

        if w < h:
            temp = w
            sizes[i][0] = h
            sizes[i][1] = w
        

    col_max = [max(col) for col in zip(*sizes)]
    result = col_max[0] * col_max[1]
    
    
    return result

