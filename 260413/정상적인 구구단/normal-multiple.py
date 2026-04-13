
N = int(input())

for i in range(1,N+1):
    row = [f"{i} * {j} = {i*j}" for j in range(1, N+1)]
    print(", ".join(row))
   

