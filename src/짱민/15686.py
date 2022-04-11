import sys;input=sys.stdin.readline
from collections import deque
from itertools import combinations
def bfs(a,b):
    global N
    Q=deque()
    Q.append((a,b,0))
    while Q:
        x,y,count=Q.popleft()
        for abc in range(4):
            mx=x+dx[abc]
            my=y+dy[abc]
            if 0<=mx<N and 0<=my<N:
                if grid[mx][my]==2:
                    return count+1
                Q.append((mx,my,count+1))





N,M=map(int,input().split())
grid=list(list(map(int,input().split())) for _ in range(N))

ans=0
dx=[0,0,1,-1]
dy=[1,-1,0,0]
chicken=[]
house=[]
for i in range(N):
    for j in range(N):
        if grid[i][j]==2:
            chicken.append((i,j))
            grid[i][j]=-1
        elif grid[i][j]==1:
            house.append((i,j))
chickenCombo=combinations(chicken,M)

def dist(x1,y1,x2,y2):
    return abs(x1-x2)+abs(y1-y2)
    

ans=sys.maxsize
for aaa in chickenCombo:
    temp=0
    for xxx,yyy in house:
        d=sys.maxsize
        for xxxx,yyyy in aaa:
            d=min(d,dist(xxx,yyy,xxxx,yyyy))
        temp+=d
    ans=min(temp,ans)
    
print(ans)
