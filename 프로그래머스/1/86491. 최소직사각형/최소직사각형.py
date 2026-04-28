import sys, math, copy


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

