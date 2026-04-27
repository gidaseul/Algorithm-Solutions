word1 = input()
word2 = input()

# Please write your code here.
w1 = sorted(word1)
w2 = sorted(word2)

num = 0
ans = "Yes"
for i in w1:
    if w2[num] == i:
        if len(w1) == num-1:
            break
        else:
            num+=1
            continue

    else:
        ans = "No"
        break

    

print(ans)