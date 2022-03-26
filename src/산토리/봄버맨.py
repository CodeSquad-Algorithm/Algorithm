from copy import deepcopy
R, C, N = list(map(int, input().split()))

# 초기 board를 순회하며 폭탄 위치를 마킹한다.
# 그리고 새 board를 만들면서 폭탄이 없는 곳에는 모두 폭탄을 배치한다.
# 그것이 2초 뒤의 초기 board 상태일 것이다.
# 그로부터 1초 뒤에 기록했던 폭탄 위치를 돌아다니며 폭발시키고 board를 업데이트한다.

t = 0

dir = [(0,1), (0,-1), (-1,0), (1,0)]

# bombs = []
def get_answer():

    board = []

    for r in range(R):
        board.append(list(input()))

    
    new_board = [["O" for _ in range(C)] for _ in range(R)]
    t = 0 

    bombs = [] 

    t += 1
    if t >= N:
        return board        

    while True:
        for r in range(R):
            for c in range(C):
                if board[r][c] == 'O':
                    bombs.append((r,c))
        board = deepcopy(new_board)
        t += 1 
        if t >= N:
            return board

        while bombs:
            r, c = bombs.pop()
            board[r][c] = '.'
            for dr, dc in dir:
                nr, nc = r+dr, c+dc
                if 0 <= nr < R and 0 <= nc < C:
                    board[nr][nc] = '.'
        t += 1
        if t >= N:
            return board

result = get_answer()
for r in result:
    print("".join(r))