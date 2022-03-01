import sys;input=sys.stdin.readline
sys.setrecursionlimit(10**8)
N,M=map(int,input().split())

graph=[ [] for _ in range(N+1) ]
visit=[ False for _ in range(N+1)]

for _ in range(M):
    a,b=map(int,input().split())
    graph[a].append(b)
    graph[b].append(a)

def dfs(i):
    visit[i]=True
    for x in graph[i]:
        if visit[x]==False:
            dfs(x)

ans=0
for i in range(1,N+1):
    if visit[i]==False:
        dfs(i)
        ans+=1

print(ans)
