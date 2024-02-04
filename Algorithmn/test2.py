class Solution:
    def isValidSudoku(self, board: list[list[str]]) -> bool:
        res = []
        for i in range(9):
            for j in range(9):
                element = board[i][j]
                if element != '.':
                    res += [(i, element), (element, j), (i // 3, j // 3, element)]
        return len(res) == len(set(res))    # set을 통해 중복을 없앤 것과 길이가 같으면 True 아니면 False


        ''' 
        for i in range(9):
            for j in range(9):
                if board[i][j] != ".":
                    target = board[i][j]
                    target_i, target_j = i, j
                else:
                    continue

                for k in range(9):  # 같은 행에 같은 숫자가 있는지 확인
                    if target_j == k:
                        continue
                    else:
                        if target == board[i][k]:
                            print("j")
                            return False

                for l in range(9):  # 같은 열에 같은 숫자가 있는지 확인
                    if target_i == l:
                        continue
                    else:
                        if target == board[l][j]:
                            return False

                for n in range(9): # 3x3박스안에 같은 숫자 있는지 확인
                    for m in range(9):
                        if n == target_i and m == target_j:
                            continue

                        if n//3 == target_i//3 and m//3 == target_j//3:
                            if target == board[n][m]:
                                return False
        return True
        '''




  
sol = Solution()
print(sol.isValidSudoku([["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]))
    
    