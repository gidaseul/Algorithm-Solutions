import math

m1, d1, m2, d2 = map(int, input().split())

def to_days(m, d):
    return sum(num_of_days[1:m]) + d  # 1월부터 m-1월까지 합산 + d

#                  1.  2.  3.  4.  5.  6.  7.  8.  9. 10. 11. 12.
num_of_days = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
day_of_the_week = ["Mon", "Tue", "Wed","Thu","Fri","Sat","Sun"]


num1 = to_days(m1, d1)
num2 = to_days(m2, d2)

diff = num2- num1

idx = diff % 7
print(day_of_the_week[idx])
