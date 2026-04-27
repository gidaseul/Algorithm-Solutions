n, k, t = input().split()
n, k = int(n), int(k)
str = [input() for _ in range(n)]

# Please write your code here.
str.sort()
for s in str:
    if s.startswith(t):
        count+=1
        if count == k:
            print(s)
            break