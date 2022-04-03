import sys
def get_answer():

    N, M = list(map(int, input().split()))

    dists = [sys.maxsize for _ in range(N+1)]
    dists[1] = 0

    graph = []
    for _ in range(M):
        a, b, c = list(map(int, input().split()))
        graph.append((a,b,c))

    answer = []
    # 일단 시작점에서 각 점까지의 최단거리를 구한다
    # 음의 가중치가 존재하므로 경로 상에 음의 가중치를 가지는 사이클이 발생하면 -1을 출력해야 한다
    # 경로가 없는 경우에는 -1을 출력한다
    for i in range(N):
        for edge in graph:
            a, b, c = edge
            if dists[a] != sys.maxsize and dists[a] + c < dists[b]:
                dists[b] = dists[a] + c
                if i == N-1:
                    print(-1)
                    return

    for dist in dists[2:]:
        if dist == sys.maxsize:
            print(-1)
        else:
            print(dist)


get_answer()