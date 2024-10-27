def DFS(n):
    if(n==0):
        return
    else:
        DFS(n-1)
        print(n)
DFS(3)