m1, d1, m2, d2 = map(int, input().split())


#                 1.  2.  3.  4.  5.  6.  7.  8.  9. 10. 11. 12.
num_of_days = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]


length = m2-m1
result = num_of_days[m1] - d1+1
check = True

for i in range(m1+1,m2):
    if length == 0:
        check = False
        break
    
    result += num_of_days[i]


if check:
    result += d2
    print(result)
else:
    print(d2-d1+1)

