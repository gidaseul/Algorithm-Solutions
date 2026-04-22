# def solution(a, d, included):
#     total = 0  # 합계를 저장할 변수
#     for i in range(len(included)):
#         if included[i]:  # included[i]가 True일 때만
#             total += a + (d * i)  # i번째 항 계산 후 더함
#     return total

## 이건 다른 사람 풀이인데 명쾌했다고 생각한 거
def solution(a, d, included):
    answer = 0
    for i in range(len(included)):
        answer += (a + d * i) * int(included[i]) # int로 하면 0,1로 나오는 값을 flase면 어차피 0으로 처리되어서 
    return answer

