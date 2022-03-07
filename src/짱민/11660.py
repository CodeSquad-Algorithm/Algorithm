import sys;input=sys.stdin.readline

N,M=map(int,input().split())
grid=list(list(map(int,input().split())) for _ in range(N))
psum=list( [0]*(N+1) for _ in range(N))

for i in range(N):
    for j in range(1,N+1):
        psum[i][j]=psum[i][j-1]+grid[i][j-1]


for _ in range(M):
    x1,y1,x2,y2=map(int,input().split())
    ans=0
    x1-=1
    y1-=1
    
    for i in range(x1,x2):
        ans += psum[i][y2]-psum[i][y1]
    print(ans)
