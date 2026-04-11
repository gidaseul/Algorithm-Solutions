K = int(input())

stack = []
total = 0

for _ in range(K):
    num = int(input())
    
    if num ==0:
       if stack:
           removed = stack.pop()
           total -= removed
    else:
        stack.append(num)
        total += num
print(total)