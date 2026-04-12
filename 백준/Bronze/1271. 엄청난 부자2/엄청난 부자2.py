import sys

input = sys.stdin.readline
N, M = map(int, input().split())

result = N//M
remain = N%M

print(result)
print(remain)