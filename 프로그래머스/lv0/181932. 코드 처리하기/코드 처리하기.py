def solution(code):
    mode = 0
    ret = ""  # 결과를 저장할 문자열
    for i in range(len(code)):
        if mode == 0:
            if code[i] == "1":
                mode = 1  # 모드 전환
            else:
                if i % 2 == 0:  # 짝수 인덱스 처리
                    ret += code[i]
        elif mode == 1:
            if code[i] == "1":
                mode = 0  # 모드 전환
            else:
                if i % 2 != 0:  # 홀수 인덱스 처리
                    ret += code[i]
    
    # 결과가 빈 문자열일 경우 "EMPTY" 반환
    return ret if ret else "EMPTY"
