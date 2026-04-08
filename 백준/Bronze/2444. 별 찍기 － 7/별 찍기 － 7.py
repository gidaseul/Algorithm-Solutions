n = int(input())
for i in range(1, n): #1부터 n-1까지 반복을 돈다,
    print(' '*(n-i) + '*'*(2*i-1)) # 공백을 n-i번 만큼 돌린다. 여기서 앞에 공백만 채우고 별 출력하게 하는데 +2로 홀수 개씩 맞춰주게 한다,
for i in range(n,0,-1):
    print(' '*(n-i)+'*'*(2*i-1))
