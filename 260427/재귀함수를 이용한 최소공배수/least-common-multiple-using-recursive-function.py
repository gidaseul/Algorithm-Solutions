


n = int(input())
num = list(map(int, input().split()))

def gcd(a,b):
    '''
    유클리드 호제법으로 최대 공약수 계산(재귀)
    - a % b == 0 이면, b가 최대공약수
    - 아니면 gcd(b, a%b) 로 재귀 호출
    '''
    if b == 0:
        return a
    return gcd(b,a%b)

def lcm(a,b):
    """ 두 수의 최대 공배수 """
    return a* b // gcd(a,b)


def lcm_of_array(arr):
    """
    배열 전체의 lcm을 재귀적으로 계산
    - 배열 정렬 없이 순서대로 누적
    - lcm(a,b,c) = lcm(lcm(a,b),c) 성질 활용
    """
    if len(arr) == 1:
        return arr[0]
    
    first_lcm = lcm(arr[0],arr[1])
    return lcm_of_array([first_lcm] + arr[2:])


print(lcm_of_array(num))
