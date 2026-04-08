import sys
n = int(input())
data = [int(sys.stdin.readline()) for i in range(n)]
set(data)
data.sort()
for i in range(n):
  sys.stdout.write(str(data[i])+"\n")