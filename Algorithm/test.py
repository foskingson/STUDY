from typing import List

class Solution:
    def grayCode(self, n: int) -> List[int]:
        res = [0]  # 초기에 0 추가
            
        for i in range(n):
            for j in range(len(res) - 1, -1, -1):
                res.append(res[j] | (1 << i))
            # 현재 결과에 있는 숫자들을 역순으로 읽어와서 맨 앞에 1을 추가하여 결과에 추가
            # 예를 들어 01(1) | 10(2)가 되면 비트 or 연산을 통해 11(3)이 됨
        return res

# Example usage:
sol = Solution()
print(sol.grayCode(3))  # Output will be the list of gray codes for n = 3

