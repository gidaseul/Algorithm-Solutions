N = int(input())
result = 0
for i in range(1,101):
    result += i
    if result == N:
        print(i)
        break

    