# def solution(num_list):
#     a = 1
#     b = 0
#     for i in range(len(num_list)):
#         a *= num_list[i]
#         b += num_list[i]
#     if a < (b**2):
#         return 1 
#     else:
#         return 0

# 참고한 코드
def solution(num_list):
    a=1
    b=0
    for x in num_list:
        a*=x
        b+=x
    if a<b*b: return 1
    return 0