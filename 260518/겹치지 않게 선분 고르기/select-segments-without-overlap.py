n = int(input())

result = []
for _ in range(n):
    a,b = map(int,input().split())
    result.append((a,b))

check_list = [False] * 1001 # 방문 확인하기 위한 것

def search(x,y):
    for i in range(x,y+1):
        if check_list[i]:
            # 충돌 한 상황 -> 원상 복구 진행
            for k in range(x,i):
                check_list[k] = False
            return False
        check_list[i] = True
    return True

def search_reset(x,y):
    for i in range(x,y+1):
        check_list[i] = False

def choose(start):
    best = 0
    for i in range(start, n):
        x, y = result[i][0], result[i][1]
        if search(x,y):
            best = max(best, 1+ choose(i+1))
            search_reset(x,y)

    return best
print(choose(0))
            