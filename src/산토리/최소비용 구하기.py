import sys, heapq
from collections import defaultdict

input = sys.stdin.readline

N = int(input())
M = int(input())

# 단방향 그래프 작성
graph = defaultdict(list)

for _ in range(M):
    u, v, w = list(map(int, input().split()))
    graph[u].append((v, w))

# 다익스트라 알고리즘의 출발점/도착점
dep, dest = list(map(int, input().split()))

# cost: 최소비용 저장 딕셔너리
cost = defaultdict(int)

# 우선순위 큐: [비용, 정점]
heap = []
heapq.heappush(heap, (0, dep))

while heap:
    cur_cost, cur_node = heapq.heappop(heap)
    if cur_node not in cost:
        cost[cur_node] = cur_cost
        for adj_node, adj_cost in graph[cur_node]:
            next_cost = cur_cost + adj_cost
            heapq.heappush(heap, (next_cost, adj_node))

print(cost[dest])