n = int(input())
nums = list(map(int, input().split()))

# Please write your code here.
num_up = sorted(nums)
num_down = list(reversed(num_up))

print(*num_up)
print(*num_down)