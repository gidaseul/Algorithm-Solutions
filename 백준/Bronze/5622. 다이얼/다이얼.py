string = input()
list_string = list(string.upper())  # 입력 문자열을 대문자로 변환

count = 0
for i in list_string:
    if i in ('A', 'B', 'C'):
        count += 3
    elif i in ('D', 'E', 'F'):
        count += 4
    elif i in ('G', 'H', 'I'):
        count += 5
    elif i in ('J', 'K', 'L'):
        count += 6
    elif i in ('M', 'N', 'O'):
        count += 7
    elif i in ('P', 'Q', 'R', 'S'):
        count += 8
    elif i in ('T', 'U', 'V'):
        count += 9
    elif i in ('W', 'X', 'Y', 'Z'):
        count += 10

print(count)
