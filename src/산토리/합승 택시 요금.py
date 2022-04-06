import sys


def solution(n, s, a, b, fares):
    answer = sys.maxsize

    # 두 지점 간 최단거리 배열 == dp 배열
    dist = [[0 if i == j else sys.maxsize for i in range(n + 1)] for j in range(n + 1)]

    # 그래프를 통해 초기거리 값 설정
    for fare in fares:
        v, w, cost = fare
        dist[v][w] = cost
        dist[w][v] = cost

    # k번 노드를 거쳐 i->j번 노드로 가는 경우를 모두 조사한다. i -> j보다 작으면 최단거리가 업데이트된다.
    # 이 때, 경유점인 k부터 검사해야 하는 이유는 최단 경로 경유점에 k가 속하는 경우부터 모두 업데이트해야 한다.

    for k in range(1, n + 1):
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])

    # 각 점을 경유해서 s로부터 a, b에 도달하는 거리의 합 중 최소를 구한다.
    for i in range(1, n + 1):
        answer = min(answer, dist[s][i] + dist[i][a] + dist[i][b])

    return answer