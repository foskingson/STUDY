class Solution:
    def isValid(self, s: str) -> bool:
        if len(s)<=1:
            return False
        check=[]
        temp=s
        i=0
        while temp:
            while temp[i]=='(' or temp[i]=='{' or temp[i]=='[':
                if temp[i]=='(':
                    temp=temp[i+1:]
                    check.append(1)
                    if temp=='':
                        return False
                if temp[i]=='{':
                    temp=temp[i+1:]
                    check.append(2)
                    if temp=='':
                        return False
                if temp[i]=='[':
                    temp=temp[i+1:]
                    check.append(3)
                    if temp=='':
                        return False
      

            if not check:
                return False
            checkPop=check.pop()
            if checkPop==1:
                if not temp or temp[i]!=')':
                    return False
            if checkPop==2:
                if not temp or temp[i]!='}':
                    return False
            if checkPop==3:
                if not temp or temp[i]!=']':
                    return False
            temp=temp[i+1:]
            if (not check and len(temp)==1) or (not temp and check):
                return False
        return True


        
            

sol = Solution()
print(sol.isValid("[{()}]"))
    
    