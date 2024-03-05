class Solution:
    def isNumber(self, s: str) -> bool:
        s = s.lower()
        if s.count('e') > 1:
            return False
        if 'e' not in s: 
            return self.isDecimal(s) or self.isInteger(s)
        else:
            idx = s.find('e')
            d_or_i = s[0:idx]
            integer = s[idx+1:len(s)]
            return (self.isDecimal(d_or_i) or (self.isInteger(d_or_i))) and self.isInteger(integer)


    def isDecimal(self, s:str) -> bool: 
        dot = digit = sign = False
        for i in range(len(s)): 
            if s[i].isdigit():
                digit = True
            elif s[i] in "+-":
                if i != 0: 
                    return False
            elif s[i] == ".":
                if dot: 
                    return False
                dot = True
            else:
                return False
        return dot and digit
    def isInteger(self, s:str) -> bool: 
        digit = False
        for i in range(len(s)):
            if s[i].isdigit():
                digit = True
            elif s[i] in "+-":
                if i != 0: 
                    return False
            else:
                return False
        return digit

sol=Solution()
print(sol.isNumber("3.14e2"))