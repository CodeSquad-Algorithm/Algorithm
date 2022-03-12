import sys

N = int(input())

dp = [-1 for _ in range(N+1)]

# 역으로 1에서 10을 만들려면?
# 1부터 N까지 올라가면서 최솟값 dp로 찾기

for num in range(1, N+1):
    cases = [dp[num-1]]
    if num % 2 == 0:
        cases.append(dp[num//2])
    if num % 3 == 0:
        cases.append(dp[num//3])
    dp[num] = min(cases) + 1

print(dp[-1])