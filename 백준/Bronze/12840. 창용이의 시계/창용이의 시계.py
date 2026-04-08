import sys

# 함수
def to_hms(sec):
    # 시계의 범위를 넘어서지 않도록 모듈러 연산을 한다.
    sec %= (24 * 3600)

    h = sec // 3600
    sec %= 3600
    m = sec // 60
    sec %= 60
    s = sec

    print(h, m, s)


# 입력
h, m, s = map(int, sys.stdin.readline().split())
seconds = s + m * 60 + h * 3600  # h m s 형태를 n초 형태로 변환하여 seconds에 저장

q = int(sys.stdin.readline())

for _ in range(q):
    # 입력의 개수가 2개 또는 1개이므로 리스트로 받는다.
    T = list(map(int, sys.stdin.readline().split()))
    # 시계 조작
    if T[0] == 1:
        seconds += T[1]
    elif T[0] == 2:
        seconds -= T[1]
    else:
        to_hms(seconds)