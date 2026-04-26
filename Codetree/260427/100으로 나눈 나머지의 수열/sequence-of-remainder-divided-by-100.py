N = int(input())

# Please write your code here.
arr = []
def func(i):
    if i == N:
        return arr[i-1]

    if i == 0:
        arr.append(2)
        return func(i+1)
    elif i == 1:
        arr.append(4)
        return func(i+1)

    else:
        result = (arr[i-1] * arr[i-2]) % 100
        arr.append(result)
        return func(i+1)

print(func(0))
