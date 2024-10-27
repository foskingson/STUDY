class Solution:
    def minDistance(self, word1: str, word2: str) -> int: # 릿코드 72번
        len1 = len(word1)
        len2 = len(word2)
        # dp[i][j]
        # word1 의 i까지 부분문자열을 word2의 j까지의 부분문자열로 교체하기 위한 최소 작업 수
        # abc,def / dp[1][1]: a => d 1번
        dp=[[0] * (len2 + 1) for _ in range(len1 + 1)]
       
        # 초기화
        for i in range(len1+1): # i: word1의 부분 문자열 / k가 0일때 즉 공백일때 부분문자열로 교체하기 위한 최소 작업 수는 word1의 길이
                dp[i][0]=i
                
        for k in range(len2+1):
                dp[0][k]=k
        
        # 루프
        for i in range(1,len1+1):
            for k in range(1,len2+1):
                if word1[i-1]==word2[k-1]:
                    dp[i][k]=dp[i-1][k-1]
                else:
                    dp[i][k]=min(dp[i-1][k-1],dp[i-1][k],dp[i][k-1])+1        
        print(dp)

        # 리턴 
        return dp[len1][len2]
    
sol=Solution()
print(sol.minDistance("horse","ros"))

#       ""  r  o  s
# ""  [ 0, 1, 2, 3]
# h   [ 1, 1, 2, 3]
# o   [ 2, 2, 1, 2]
# r   [ 3, 2, 2, 2]
# s   [ 4, 3, 3, 2]
# e   [ 5, 4, 4, 3]