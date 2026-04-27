word1 = input()
word2 = input()

word1=sorted(word1)
word2=sorted(word2)

word1=''.join(word1)
word2=''.join(word2)

if word1 == word2:
    print('Yes')
else:
    print('No')

