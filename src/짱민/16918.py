import sys;input=sys.stdin.readline
R,C,N=map(int,input().split())
grid=list( list(input().rstrip()) for _ in range(R)  )


def install():
    for i in range(R):
        for j in range(C):
            if grid[i][j]==".":
                grid[i][j]=1

def plus_time():
    for i in range(R):
        for j in range(C):
            if grid[i][j]!=".":
                grid[i][j]+=1
    

def baam():
    bomb_set=set()
    for i in range(R):
        for j in range(C):
            if grid[i][j]>=3:
                bomb_set.add((i,j))

    for x,y in bomb_set:
        for j in range(5):
            mx=x+dx[j]
            my=y+dy[j]
            if 0<=mx<R and 0<=my<C:
                grid[mx][my]="."

dx=[0,0,1,-1,0]
dy=[1,-1,0,0,0]
for i in range(R):
    for j in range(C):
        if grid[i][j]=="O":
            grid[i][j]=1



time=1
while time!=N:
    plus_time()
    temp_time=time%2

    #if temp_time==0:
    #    pass

    if temp_time==1:
        install()
        

    elif temp_time==0:
        baam()


    time+=1

for i in range(R):
    for j in range(C):
        if grid[i][j]==".":
            print(".",end='')
        else:
            print("O",end='')
    print()
