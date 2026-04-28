OFFSET = 100000
SIZE = OFFSET * 2 + 1

n = int(input())
commands = []

for _ in range(n):
    xi, di = input().split()
    commands.append((int(xi), di))

pos = OFFSET

white_cnt = [0] * SIZE
black_cnt = [0] * SIZE
last_color = [0] * SIZE  # 1=흰, 2=검

for x,d in commands:
    if d == "R":
        for j in range(x):
            black_cnt[pos+j] += 1
            last_color[pos+j] = 2
        pos += x-1

    elif d == "L":
        for j in range(x):
            white_cnt[pos-j] += 1
            last_color[pos-j] = 1
        pos -= x-1

white = black = grey = 0

for i in range(SIZE):
    if white_cnt[i] >= 2 and black_cnt[i] >=2:
        grey+=1
    
    elif last_color[i] == 1:
        white += 1
    elif last_color[i] == 2:
        black += 1

print(white, black, grey)