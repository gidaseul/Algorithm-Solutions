import math

m1, d1, m2, d2 = map(int, input().split())

#                  1.  2.  3.  4.  5.  6.  7.  8.  9. 10. 11. 12.
num_of_days = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
day_of_the_week = ["Mon", "Tue", "Wed","Thu","Fri","Sat","Sun"]


num1 = num_of_days[m1] + d1

num2 = num_of_days[m2] + d2

idx = 0
if num2-num1 <0:
    idx = abs(num2-num1) % 7
    print(day_of_the_week[-idx])

else:
    idx = (num2-num1) % 7    
    print(day_of_the_week[idx])