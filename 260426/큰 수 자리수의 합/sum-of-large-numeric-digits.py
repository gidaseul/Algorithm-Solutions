a, b, c = map(int, input().split())

# Please write your code here.
result = a*b*c
result = str(result)

sum = 0
for i in range(len(result)):
    sum += int(result[i])

print(sum)