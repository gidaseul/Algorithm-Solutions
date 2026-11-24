n = int(input())
f = int(input())

base = n - n % 100          # 뒤 두 자리를 00으로
for last in range(100):
    if (base + last) % f == 0:
        print(f"{last:02d}")  # 한 자리면 앞에 0을 붙임
        break
