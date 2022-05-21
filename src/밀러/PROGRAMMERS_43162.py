import collections
from typing import List, Dict


def solution(n: int, computers: List[List[int]]):
    graph: Dict[int, List[int]] = collections.defaultdict(list)

    for i in range(n):
        for j in range(n):
            if i != j and computers[i][j]:
                graph[i].append(j)

    visited: List[int] = [0 for _ in range(n)]

    def dfs(now: int):
        for dest in graph[now]:
            if visited[dest]:
                continue

            visited[dest] = True
            dfs(dest)

    count: int = 0
    for i in range(n):
        if not visited[i]:
            dfs(i)
            count += 1

    return count


if __name__ == "__main__":
    print(solution(3, [[1, 1, 0], [1, 1, 0], [0, 0, 1]]))
    print(solution(3, [[1, 1, 0], [1, 1, 1], [0, 1, 1]]))
