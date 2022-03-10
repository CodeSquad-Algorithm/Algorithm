import sys
# 1번 명령: x번째 좌석에 사람을 태워라 -> index 활용 시 O(1)
# 2번 명령: x번째 좌석의 사람은 하차한다 -> hashMap 활용 시 O(1)
# 3번 명령: 한칸씩 밀려난다 -> O(X)
# 4번 명령: 한칸씩 앞으로 간다 -> O(X) 

# 각 열차의 탑승상태를 set으로 관리한다 -> 중복된 상태는 통과할 수 없다.

input = sys.stdin.readline

N, M = list(map(int, input().split()))
MAX_POS = 20
trains = dict()

for i in range(1, N+1):
    trains[i] = dict()

for _ in range(M):
    cmd = list(map(int, input().split()))
    if len(cmd) == 3:
        mode, idx, pos = cmd
        if mode == 1:
            if trains[idx].get(pos) is None:
                trains[idx][pos] = True
        else:
            if trains[idx].get(pos) is not None:
                trains[idx].pop(pos)
    else:
        mode, idx = cmd
        train = trains[idx]
        if not train:
            continue
        temp = False
        if mode == 3:
            for p in range(1, MAX_POS+1):
                if train.get(p) is not None:
                    if not temp:
                        train. pop(p)
                    temp = True
                else:
                    if temp:
                        train[p] = True
                        temp = False
        else:
            temp = False
            for p in range(MAX_POS, 0, -1):
                if train.get(p) is not None:
                    if not temp:
                        train.pop(p)
                    temp = True
                else:
                    if temp:
                        train[p] = True
                        temp = False


result = set()

for train in trains.values():
    
    result.add(tuple(sorted(train)))

print(len(result))