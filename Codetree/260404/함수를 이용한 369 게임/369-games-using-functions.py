a, b = map(int, input().split())

# Please write your code here.
def cal (a, b):
    count = 0
    for i in range(a,b+1):
        if(i % 3 == 0):
            count += 1
        elif "3" in str(i):
            count +=1
        elif "6" in str(i):
            count +=1
        elif "9" in str(i):
            count +=1
        
        
    return print(count)

cal(a,b)
