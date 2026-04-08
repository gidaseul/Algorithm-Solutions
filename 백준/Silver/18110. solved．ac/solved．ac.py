import sys
input = sys.stdin.readline

def custom_round(x):
    return int(x + 0.5)

n = int(input())
if n == 0:
    print(0)
    exit()

lines = [int(input()) for _ in range(n)]
lines.sort()

cut = custom_round(n * 0.15)
lines = lines[cut : n - cut]

print(custom_round(sum(lines) / len(lines)))
