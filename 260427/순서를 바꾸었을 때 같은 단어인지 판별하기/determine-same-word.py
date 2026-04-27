word1 = input()
word2 = input()

# Please write your code here.
w1 = sorted(word1)
w2 = sorted(word2)

w1_l = len(w1)
w2_l = len(w2)
ans = "Yes"

for i in range(w1_l):
    if w1_l != w2_l:
        ans = "No"
        break 
    
    if w1[i] == w2[i]:
        continue

    else:
        ans = "No"
        break


print(ans)