string = input()

arr = list(string)

arr[2] = "a"
arr[-2] = "a"

result = ''.join(arr)
print(result)