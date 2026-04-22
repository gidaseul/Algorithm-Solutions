def solution(num_list):
    a ='' # 홀수 용
    b ='' # 짝수 용
    for x in num_list:
        if x % 2 ==0:
            b += str(x)
        else :
            a += str(x)
    answer = int(a) + int(b)
    return answer