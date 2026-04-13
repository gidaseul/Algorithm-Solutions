arr = ["apple", "banana", "grape", "blueberry", "orange"]

s = input()
cnt = 0
for a in arr:
    if a[2] == s or a[3] == s:
        print(a)
        cnt+=1

print(cnt)