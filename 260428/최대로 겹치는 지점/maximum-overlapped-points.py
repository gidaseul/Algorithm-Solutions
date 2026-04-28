n = int(input())
segments = [tuple(map(int, input().split())) for _ in range(n)]

# Please write your code here.
arr = [0] * 101

for i in segments:
    s, e = i[0],i[1]

    for a in range(s,e+1):
        arr[a] += 1

print(max(arr))