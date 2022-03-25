def solution(places):
    answer = []
    N = len(places[0])
    # 응시자 상하좌우로 검사해서 파티션이 없으면 -> 그 방향 검사
    # 상하좌우에 응시자 존재하면 거리두기 미준수
    
    dir = [(0,1), (1,0), (0,-1), (-1,0)]
    def check_place(place):
        for r in range(N):
            for c in range(N):
                if place[r][c] == 'P':
                    for dr, dc in dir:
                        nr, nc = r+dr, c+dc
                        if 0 <= nr < N and 0 <= nc < N:
                            if place[nr][nc] == 'O':
                                for er, ec in dir:
                                    mr, mc = nr+er, nc+ec
                                    if (mr, mc) == (r, c):
                                        continue
                                    if 0 <= mr < N and 0 <= mc < N:
                                        if place[mr][mc] == 'P':
                                            answer.append(0)
                                            return
                            elif place[nr][nc] == 'P':
                                answer.append(0)
                                return
        answer.append(1)
        return
    
    for place in places:
        check_place(place)
    
    return answer