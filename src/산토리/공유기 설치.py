import sys
input = sys.stdin.readline
# 최단 거리를 정해놓고 충족하는 지 검사한다.
# 최단 거리를 정할 때 이분 탐색을 수행한다.
# 최단 거리에 따라 정렬된 집 좌표를 하나씩 건너 뛰며 지정한 거리 미만이면 해당 집에 공유기를 설치하지 않았다고 판단한다.
# 마지막까지 갔을 때 설치해야 하는 공유기 개수보다 적으면 거리가 큰 것이므로 범위를 줄여 다시 탐색한다.
# 설치해야 하는 공유기 개수보다 많거나 같으면 거리가 작은 것이므로 거리의 벙뮈를 키워 다시 탐색한다.
# 설치해야 하는 공유기 개수와 같은 경우에는 답을 기록해두고 마지막에 그 배열의 최댓값을 반환한다.

N, C = list(map(int, input().split()))

home = []

for _ in range(N):
    home.append(int(input()))

home.sort()

# print(home)

left_home, right_home = home[0], home[-1]
right = right_home - left_home
left = 0
result = []
while left <= right:

    mid = (left + right) // 2
    dist = mid

    cnt = 0
    prev = 0
    for idx, pos in enumerate(home):
        if idx == 0:
            cnt += 1
            prev = pos
            continue
        if pos - prev >= mid: # 지정한 거리보다 길게 설치가 가능할 때
            cnt += 1
            prev = pos

    if cnt < C:
        right = mid - 1

    else:
        result.append(mid)
        left = mid + 1

# print(result)
print(max(result))