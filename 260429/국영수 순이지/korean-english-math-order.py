n = int(input())

class stu:
    def __init__(self, name, kor, eng, math):
        self.name = name
        self.kor = kor
        self.eng = eng
        self.math = math

student = []

for _ in range(n):
    n_i, k_i, e_i, m_i = input().split()
    student.append(stu(n_i, k_i, e_i, m_i))
# Please write your code here.

student.sort(key=lambda x : (-x.kor,-x.eng,-x.math))

for i in student:
    print(i.name, i.kor, i.eng, i.math)