# binary = input().strip()

# # 2진수 -> 10진수
# decimal = 0
# for ch in binary:
#     decimal = decimal * 2 + int(ch)

# # 10진수 -> 8진수
# if decimal == 0:
#     print(0)
# else:
#     result = ""
#     while decimal > 0:
#         result = str(decimal % 8) + result
#         decimal //= 8
#     print(result)
x = input().strip()
print(oct(int(x, 2))[2:])
