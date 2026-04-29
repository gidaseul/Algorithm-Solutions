n = int(input())

class stu:
    def __init__(self, name, height, weight):
        self.name = name
        self.height = height
        self.weight = weight

student = []

for _ in range(n):
    n_i, h_i, w_i = input().split()
    student.append(stu(n_i, h_i, w_i))
# Please write your code here.

student.sort(key=lambda x : x.height)

for i in student:
    print(i.name, i.height, i.weight)