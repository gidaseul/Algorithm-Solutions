
n = int(input())
def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b)

def lcm(a, b):
    return a * b // gcd(a, b)

def lcm_recursive(nums, idx=0):
    if idx == len(nums) - 1:
        return nums[idx]

    return lcm(nums[idx], lcm_recursive(nums, idx + 1))


nums = list(map(int, input().split()))
print(lcm_recursive(nums))