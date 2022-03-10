# 결국은 left, right 둘 다 N번만 움직여야 된다.
# left는 0~N까지 반복하는 와중에 right를 최대한 움직여보고 M보다 커지면 left를 줄여나가는 방법으로 간다.

N, M = list(map(int, input().split()))

nums = list(map(int, input().split()))

answer = 0

result = 0
right = 0

for left in range(N):
    while result < M and right <= N-1:
        result += nums[right]
        right += 1
    
    if result == M:
        answer += 1

    # left 한 칸 이동
    result -= nums[left]

print(answer)