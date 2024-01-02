s='soon'
num=0
print(s[-1])
for x in s:
    cnt=0    
    for i in range(len(x)//2):
        print(i)
        if x[i]==x[-i-1]: 
            cnt+=1
  