isbn = input().strip()  

idx = isbn.index('*')  
total = 0

for i in range(13):
    if isbn[i] == '*':
        continue

    num = int(isbn[i])

    if i % 2 == 0:
        total += num * 1
    else:
        total += num * 3

if idx % 2 == 0:
    weight = 1
else:
    weight = 3

for x in range(10):
    if (total + x * weight) % 10 == 0:
        print(x)
        break
