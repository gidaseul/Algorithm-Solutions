m1, d1, m2, d2 = map(int, input().split())


#                 1.  2.  3.  4.  5.  6.  7.  8.  9. 10. 11. 12.
num_of_days = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

m = m2-m1-2
d1 = num_of_days[m1] - d1 +1
result = d2+d1
for i in range(m1+1, m2):
    ressult += num_of_days[i]

 
print(result)

