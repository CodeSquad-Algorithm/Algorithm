import sys;import heapq

def solution(n, s, a, b, fares):
    global graph
    graph=[ [] for _ in range(n+1) ]
    dps=[[]]

    for i,j,k in fares:
        graph[i].append((j,k))
        graph[j].append((i,k))
    
    for ii in range(1,n+1):
        dps.append(da(n,ii))
        
    answer = sys.maxsize
    answer=min(answer,dps[s][a]+dps[s][b])
    answer=min(answer,dps[s][b]+dps[s][a])
    for i in range(1,n+1):
        if i==s:
            continue
        answer=min(answer,dps[s][i]+dps[i][a]+dps[i][b])

    
    return answer


def da(n,start):
    dp=[sys.maxsize for _ in range(n+1)]
    dp[start]=0
    h=[]
    heapq.heappush(h,(start,0))
    while h:
        dest,cost=heapq.heappop(h)
        if dp[dest]<cost:
            continue
        for dest_dest,cost_cost in graph[dest]:
            if dp[dest_dest]>cost+cost_cost:
                dp[dest_dest]=cost+cost_cost
                heapq.heappush(h,(dest_dest,cost+cost_cost))
    return dp
























if __name__=="__main__":
    N,S,A,B,FARES=6,4,6,2,[[4, 1, 10],
     [3, 5, 24],
      [5, 6, 2],
      [3, 1, 41], 
      [5, 1, 24], 
      [4, 6, 50],
       [2, 4, 66], 
       [2, 3, 22], 
       [1, 6, 25]]
    solution(N,S,A,B,FARES)
