from itertools import combinations
from collections import defaultdict
import sys
# M개의 치킨집을 조합으로 뽑아서 모든 경우의 수를 조사해본다.

N, M = list(map(int, input().split()))

board = []

for _ in range(N):
    board.append(list(map(int, input().split())))

# 각 치킨집에서 각 집까지 가는 거리를 재서 저장해둔다.
# 조합에서 하나씩 꺼내면서 치킨집의 각 집별 거리를 비교하여 최단거리로만 더한다.

home = []
stores = []
dists = defaultdict(list)


for r in range(N):
    for c in range(N):
        if board[r][c] == 2:
            stores.append((r, c))
        elif board[r][c] == 1:
            home.append((r,c))

for idx, pos in enumerate(stores):
    sr, sc = pos

    for hr, hc in home:
        dist = abs(sr-hr) + abs(sc-hc)
        dists[idx].append(dist)

# print(dists)
answer = sys.maxsize

for comb in list(combinations(dists, M)):
    temp = 0
    for i in range(len(home)):
        dist = sys.maxsize
        for store in comb:
            dist = min(dist, dists[store][i])
        temp += dist
    answer = min(answer, temp)

print(answer)