from collections import deque

C, R = list(map(int, input().split()))

dir = [(0, 1), (0, -1), (-1, 0), (1, 0)]

board = []

for _ in range(R):
    board.append(list(map(int, input().split())))

visited = [[False] * C for _ in range(R)]
q = deque()

day = 0
cur_day = 0
for r in range(R):
    for c in range(C):
        if board[r][c] == 1:
            q.append((r, c, 0))
            visited[r][c] == True


while q:
    r, c, day = q.popleft()

    for dr, dc in dir:
        nr, nc = r+dr, c+dc
        if 0 > nr or nr >= R or 0 > nc or nc >= C or visited[nr][nc] or board[nr][nc] == -1:
            continue
        if board[nr][nc] == 0:
            visited[nr][nc] = True
            cur_day = day+1
            q.append((nr, nc, cur_day))
            board[nr][nc] += 1


    day = cur_day

def solve():
    for r in range(R):
        for c in range(C):
            if board[r][c] == 0:
                print(-1)
                return
    print(day)
    

solve()
