import collections

## 릿코드 76
class Solution:
    def minWindow(self, s: str, t: str) -> str:
        if len(s) < len(t):
            return ""
        
        # t의 문자 개수를 카운트
        t_count = collections.Counter(t)
        required = len(t_count)
        
        # 현재 윈도우 내 t의 문자 개수를 카운트
        window_count = collections.defaultdict(int)
        
        # 현재 윈도우 내 t의 모든 문자를 포함하는지 여부
        formed = 0
        
        # 최소 창의 길이와 해당 창의 시작, 끝 인덱스
        min_length = float('inf')
        min_window = ""
        
        # 슬라이딩 윈도우를 이동하면서 최소 창을 찾음
        left, right = 0, 0
        while right < len(s):
            char = s[right]
            window_count[char] += 1
            if char in t_count and window_count[char] == t_count[char]:
                formed += 1
            
            # 최소 창의 조건 충족 시, 왼쪽 포인터 이동
            while formed == required and left <= right:
                char = s[left]
                window_length = right - left + 1
                if window_length < min_length:
                    min_length = window_length
                    min_window = s[left:right + 1]
                
                window_count[char] -= 1
                if char in t_count and window_count[char] < t_count[char]:
                    formed -= 1
                
                left += 1
            
            right += 1
        
        return min_window

            
sol=Solution()
print(sol.minWindow("bbaa","aba"))