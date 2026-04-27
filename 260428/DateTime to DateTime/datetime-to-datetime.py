a, b, c = map(int, input().split())

# Please write your code here.

if a <=11 and b<=11 and c<11:
    print(-1)

d = a-11

h = b-11
if h <0:
    h = 24-11+b

m = c-11
 
if m <0:
    m = 60-11+b

result = (d * 24 * 60) + h * 60 + m
print(result)


