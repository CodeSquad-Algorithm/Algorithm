from collections import deque

def solution(places):
    ans=[]
    dx=[0,0,1,-1]
    dy=[1,-1,0,0]

    def bfs(ii,jj):
        Q=deque()
        Q.append((ii,jj,0))
        while Q:
            x,y,dist = Q.popleft()
            visit[x][y]=True
            for ccc in range(4):
                mx=x+dx[ccc]
                my=y+dy[ccc]
                if 0<=mx<5 and 0<=my<5 and visit[mx][my]==False and dist<2 and place[mx][my]!="X":
                    if place[mx][my]=="P":
                        return False
                    Q.append((mx,my,dist+1))
        return True

    def P_list():
        temp=[]
        for asdf in range(5):
            for asdf2 in range(5):
                if place[asdf][asdf2]=="P":
                    temp.append((asdf,asdf2))
        return temp

    for place in places:
        visit=[ [False]*5 for _ in range(5)]
        peoples=P_list()
        flag=True
        for people in peoples:
            if bfs(people[0],people[1])==False:
                ans.append(0)
                flag=False
                break
        if flag:
            ans.append(1)

    
    
    return ans



if __name__ == "__main__":
    place1=[	['POOOP', 'OXXOX', 'OPXPX', 'OOXOX', 'POXXP'],
    ['POOPX', 'OXPXP', 'PXXXO', 'OXXXO', 'OOOPP'],
    ['PXOPX', 'OXOXP', 'OXPOX', 'OXXOP', 'PXPOX'],
    ['OOOXX', 'XOOOX', 'OOOXX', 'OXOOX', 'OOOOO'],
    ['PXPXP', 'XPXPX', 'PXPXP', 'XPXPX', 'PXPXP']]
    print(solution(place1))
