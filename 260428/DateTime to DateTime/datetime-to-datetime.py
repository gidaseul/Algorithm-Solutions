# 변수 선언 및 입력
a, b, c = tuple(map(int, input().split()))

# 차이를 계산합니다.
diff = (a * 24 * 60 + b * 60 + c) - (11 * 24 * 60 + 11 * 60 + 11)

# 출력
if diff < 0:
    print(-1)
else:
    print(diff)
