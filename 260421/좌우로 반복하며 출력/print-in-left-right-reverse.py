num = int(input())

arr = []

for i in range(1,num+1):

    row = [j for j in range(1, num+1)]
    
    if i % 2==0:
        row.reverse()
    
    arr.append(row)
    print(*arr[i-1])


    
    