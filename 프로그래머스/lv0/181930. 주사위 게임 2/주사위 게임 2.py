def solution(a, b, c):
    if a == b == c:  # 세 숫자가 모두 같음
        return (a + b + c) * (a**2 + b**2 + c**2) * (a**3 + b**3 + c**3)
    elif a != b and b != c and a != c:  # 세 숫자가 모두 다름
        return a + b + c
    else:  # 두 숫자만 같음
        return (a + b + c) * (a**2 + b**2 + c**2)
