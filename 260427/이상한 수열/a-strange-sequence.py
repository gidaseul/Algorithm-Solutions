N = int(input())

# Please write your code here.
arr = [1,2]
def fun(i):

    if i==N:
        return arr[i-1]
    
    num = (i+1) // 3
    result = arr[num-1] + arr[i-1]
    arr.append(result)
    return fun(i+1)


print(fun(2))