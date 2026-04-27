m1, d1, m2, d2 = map(int, input().split())
A = input()

# Please write your code here.


def to_days(m, d):
    return sum(num_of_days[1:m]) + d  # 1월부터 m-1월까지 합산 + d

#                  1.  2.  3.  4.  5.  6.  7.  8.  9. 10. 11. 12.
num_of_days = [0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
day_of_the_week = ["Mon", "Tue", "Wed","Thu","Fri","Sat","Sun"]


num1 = to_days(m1, d1)
num2 = to_days(m2, d2)

diff = num2- num1

current_idx = day_of_the_week.index(A)

count = 1
diff -=current_idx +1
while diff >= 0:
    diff-=7
    count+=1

print(count)
