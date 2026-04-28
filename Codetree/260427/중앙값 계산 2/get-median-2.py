n = int(input())
arr = list(map(int, input().split()))

# Please write your code here.

for i in range(n):
    if i % 2==0:
        sorted_arr = sorted(arr[:i+1])
        print(sorted_arr[i//2], end=" ")