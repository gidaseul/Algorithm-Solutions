import sys

input = sys.stdin.readline
N, M = map(int, input().split())

arr = list(map(int,input().split()))
result = [0] * (N+1)

for i in range(N):
    result[i+1] = arr[i] + result[i]

ans = []

for j in range(M):
    s,e = map(int,input().split())
    ans.append(str(result[e] - result[s-1]))

sys.stdout.write("\n".join(ans)+ "\n")


