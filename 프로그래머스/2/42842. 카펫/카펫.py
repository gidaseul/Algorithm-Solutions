def solution(brown, yellow):
    result = brown + yellow
    
    for i in range(1,int(result**(1/2))+1):
        if result %i == 0:
            w = result // i
            h = i
            
            if 2*w + 2*h -4 == brown:
                return [w,h]
        
    return 0
