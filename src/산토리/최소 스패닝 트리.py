import heapq
from collections import defaultdict
V, E = list(map(int, input().split()))

graph = defaultdict(list)
edges = []

for _ in range(E):
    a, b, c = list(map(int, input().split()))
    graph[a].append((b,c))
    graph[b].append((a,c))

# heapq.heapify(edges)
visited = [False for _ in range(V+1)]
answer = 0

cnt = 0
heap = [(0, 1)]
while cnt < V:
    cost, v = heapq.heappop(heap)
    if not visited[v]:
        answer += cost
        visited[v] = True
        cnt += 1
        for w, cost in graph[v]:
            heapq.heappush(heap, (cost, w))
            

print(answer)