total_score = 0
total_subjects = 0  # 총 과목 수를 세기 위한 변수 추가

for i in range(20):
    a, b, c = input().split()
    b = float(b)  # 문자열을 실수형으로 변환하여 b에 할당
    score = 0
    if c == "A+":
        score = b * 4.5
    elif c == "A0":
        score = b * 4.0
    elif c == "B+":
        score = b * 3.5
    elif c == "B0":
        score = b * 3.0
    elif c == "C+":
        score = b * 2.5
    elif c == "C0":
        score = b * 2.0
    elif c == "D+":
        score = b * 1.5
    elif c == "D0":
        score = b * 1.0
    elif c == "F":
        score = b * 0.0
    else:
        continue
    total_score += score  # 각 등급에 따른 학점을 누적하여 합산
    total_subjects += b  # 각 과목의 학점을 누적하여 합산

if total_subjects > 0:
    k = total_score / total_subjects  # 학점 평균 계산
    print(k)
else:
    print("입력된 학점이 없습니다.")
