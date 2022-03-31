import sys;input=sys.stdin.readline
import heapq
N=int(input())
city=list([] for _ in range(N+1))
dp=list(sys.maxsize for _ in range(N+1))

M=int(input())

for _ in range(M):
    start, dest, cost = map(int,input().split())
    city[start].append([dest,cost])

start_num, dest_num = map(int,input().split())

def da():
    h=[]
    heapq.heappush(h,(start_num,0))
    while h:
        dest,cost=heapq.heappop(h)
        if dp[dest]<cost:
            continue
        for dest_dest,dest_cost in city[dest]:
            if dp[dest_dest]>cost+dest_cost:
                dp[dest_dest]=cost+dest_cost
                heapq.heappush(h, (dest_dest,dp[dest_dest]))

    
dp[start_num]=0

da()
print(dp[dest_num])
