import sys
from collections import defaultdict, deque

input = sys.stdin.readline

N, M = list(map(int, input().split()))

graph = defaultdict(list)

for _ in range(M):
    u, v = list(map(int, input().split()))
    graph[u].append(v)

visited = [0 for _ in range(N+1)]
result = 0
q = deque()

for start in range(1, N+1):
    if visited[start] == 0:
        result += 1
        q.append(start)

        while q:
            print(q)
            cur_node = q.popleft()
            visited[cur_node] = 1
            for next_node in graph[cur_node]:
                if visited[next_node] == 0:
                    q.append(next_node)
                    visited[next_node] = 1
    

print(result)