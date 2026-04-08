import sys
input = sys.stdin.readline

ascending=[1,2,3,4,5,6,7,8]
descending=[8,7,6,5,4,3,2,1]

array=list(map(int,input().split()))

if array==ascending:
    sys.stdout.write("ascending"+'\n')
elif array==descending:
    sys.stdout.write("descending"+'\n')
else:
    sys.stdout.write("mixed"+'\n')