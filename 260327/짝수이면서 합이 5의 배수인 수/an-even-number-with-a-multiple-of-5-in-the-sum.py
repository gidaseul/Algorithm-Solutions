
# Please write your code here.

def check(n):
    ten = n/10
    one = n%10
    result = ten+one
    if(n % 2 == 0 && result % 5 ==0)
        return "Yes"
    else:
        return "No"
    return "No"

n = int(input())

check(n)
