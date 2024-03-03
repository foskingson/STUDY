from typing import List


class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        
        for i in range(len(grid)):
            for k in range(len(grid[0])):
                if i==0 and k==0:
                    continue
                if i==0:
                    grid[i][k]=grid[i][k]+grid[i][k-1]
                elif k==0:
                    grid[i][k]=grid[i][k]+grid[i-1][k]
                else:
                    grid[i][k]= min(grid[i][k]+grid[i][k-1],grid[i][k]+grid[i-1][k])
        return grid[-1][-1]
                

sol=Solution()
print(sol.minPathSum([[1,3,1],[1,5,1],[4,2,1]]))