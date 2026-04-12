import sys
import math
from bisect import bisect_left

input = sys.stdin.readline
N = int(input())


arr = list(map(int, input().split()))
stack = [arr[0]]

# 스택을 통해서 오름차순으로 만든 이후 이분탐색 진행
for a in arr[1:]:
    if stack[-1] < a:
        stack.append(a)
    else:
        idx = bisect_left(stack, a)
        stack[idx] = a

print(len(stack))