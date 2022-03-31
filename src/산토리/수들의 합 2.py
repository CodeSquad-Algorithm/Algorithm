# 인덱스 0을 가리키는 포인터 두개를 만든다.
# 합이 M보다 작으면 오른쪽 포인터를 하나 늘려 두 포인터가 가리키는 합을 구한다.
# 여전히 작으면 오른쪽 포인터를 하나 늘려 합에 새 숫자를 더한다.

# 합이 M보다 크면 전부 양수이기 때문에 더 더해도 소용이 없다. 왼쪽 포인터를 하나 늘려 다음 구간을 탐색한다.
# 오른쪽 포인터도 왼쪽 포인터 위치로 옮긴다. 

# 합이 M이면 답에 기록하고 왼쪽 포인터를 한 칸 오른쪽으로 옮긴다.  

# left가 맨끝에 도달할 때까지 반복한다.

N, M = list(map(int, input().split()))

nums = list(map(int, input().split()))

answer = 0

left, right = 0, 0 
result = 0

while left <= N-1:
    result += nums[right]
    # print(result, left, right, answer)

    if result == M:
        answer += 1
        left += 1
        right = left
        result = 0

    elif result < M:
        if right < N-1:
            right += 1
        else:
            left += 1
            right = left
            result = 0

    else:
        left += 1
        right = left
        result = 0

print(answer)