n = 5

class stu:
    def __init__(self,name, height, weight):
        self.name = name
        self.height = height
        self.weight = weight



student = []

for _ in range(n):
    n, h, w = input().split()
    student.append(stu(n , int(h), float(w)))
# Please write your code here.

student.sort(key=lambda x : (x.name))
print("name")
for i in student:
    print(i.name , i.height, i.weight)

print()
student.sort(key=lambda x : (-x.height))
print("height")
for i in student:
    print(i.name , i.height, i.weight)