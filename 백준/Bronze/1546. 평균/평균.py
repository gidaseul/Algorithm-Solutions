n = int(input())
number = list(map(int, input().split()))
maximum_score = max(number)

for i in range(n):
    normalized_score = (number[i] / maximum_score) * 100
    number[i] = normalized_score

average_score = sum(number) / n

print("{:.2f}".format(average_score))