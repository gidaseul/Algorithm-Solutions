N = int(input())

people = []
for _ in range(N):
    p = tuple(map(int, input().split()))
    people.append(p)

for i in range(N):
    rank = 1
    w, h = people[i]

    for j in range(N):
        cW, cH = people[j]

        if w < cW and h < cH:
            rank+=1
    print(rank, end=' ')