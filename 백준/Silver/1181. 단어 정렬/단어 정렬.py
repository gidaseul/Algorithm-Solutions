t=int(input())
words= [str(input()) for _ in range(t)]
words=list(set(words))
words.sort()
words.sort(key=len)

for i in words:
  print(i)
