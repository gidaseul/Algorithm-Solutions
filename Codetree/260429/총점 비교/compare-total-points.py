n = int(input())

class stu:
    def __init__(self, name, kor, eng, math, result):
        self.name = name
        self.kor = kor
        self.eng = eng
        self.math = math
        self.result = result 

student = []

for _ in range(n):
    n_i, k_i, e_i, m_i = input().split()
    k_i, e_i, m_i = int(k_i), int(e_i), int(m_i)
    result = k_i + e_i + m_i
    student.append(stu(n_i, k_i, e_i, m_i, result))
# Please write your code here.

student.sort(key=lambda x : (x.result))

for i in student:
    print(i.name, i.kor, i.eng, i.math)