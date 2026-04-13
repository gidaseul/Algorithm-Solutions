a ,b = map(str, input().split())

aS = len(a)
bS = len(b)

if aS == bS:
    print("same")
elif aS > bS:
    print(a +" "+ str(aS))
else:
    print(b +" "+ str(bS))