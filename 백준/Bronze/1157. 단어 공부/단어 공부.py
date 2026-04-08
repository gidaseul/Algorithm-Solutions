str=input().upper()
string=list(set(str))

c=[]
for i in string:
  count = str.count(i)
  c.append(count)

if c.count(max(c))>=2:
  print("?")
else:
  print(string[(c.index(max(c)))])



