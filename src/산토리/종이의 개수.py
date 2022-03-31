# 스택에 직접 배열을 다 담지말고 for문을 탐색할 인덱스만 기록하자
# 탐색하는 함수를 따로만들어서 답 여부를 확인한다

N = int(input())

board = []

for _ in range(N):
    board.append(list(map(int, input().split())))


stack = []
stack.append([0,0,N,N])

answer = [0] * 3

def check(r1, c1, r2, c2):
    start = board[r1][c1]

    if r1 == r2 and c1 == c2:
        return True

    for r in range(r1, r2):
        for c in range(c1, c2):
            if start != board[r][c]:
                return False

    return True

def get_answer(stack):
    while stack:
        r1, c1, r2, c2 = stack.pop()

        if check(r1, c1, r2, c2):
            answer[board[r1][c1]+1] += 1

        else:
            unit = (r2-r1)//3

            if unit == 1:
                for i in range(r1, r2):
                    for j in range(c1, c2):
                        answer[board[i][j]+1] += 1
                continue
            
            # print(unit)
            for i in range(3):
                for j in range(3):
                    stack.append([r1+unit*i, c1+unit*j, r1+unit*(i+1), c1+unit*(j+1)])                         
get_answer(stack)
for ans in answer:
    print(ans)