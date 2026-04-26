a, b, c = map(int, input().split())

# Please write your code here.
result = a*b*c
def digit_sum(n):
    if n < 10:
        return n

    return digit_sum(n//10) + n%10

print(digit_sum(result))