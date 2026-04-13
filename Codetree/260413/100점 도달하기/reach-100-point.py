N = int(input())

result = []

for i in range(N,101):
    if i >= 90:
        result.append("A")
    elif i>=80:
        result.append("B")
    elif i>= 70:
        result.append("C")
    elif i>= 60:
        result.append("D")
    else:
        result.append("F")
print(*result)
