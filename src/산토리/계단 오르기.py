# 1칸씩 오르거나 2칸씩 오르거나 할 수 있다.
# 세칸을 연속으로 오를 수는 없다.
# 마지막 계단을 무조건 밟아야 한다.

N = int(input())

nums = []

for _ in range(N):
    nums.append(int(input()))

dp = [[0,0] for _ in range(N)] # dp[m][0]: 1칸씩 이동한 경우, dp[m][1]: 2칸씩 이동한경우

dp[0][0] = nums[0]
dp[0][1] = 0
dp[1][0] = dp[0][0] + nums[1]
dp[1][1] = nums[1]

# n칸에 도착하는 경우는 n-1칸에 오거나 n-2칸에 오거나
# n-1칸에서 온경우는 n-3칸으로부터 왔어야 한다. n-2칸에서 온경우는 n-3칸에서 오거나 n-4칸에서 오거나

for i in range(2, N):
    dp[i][0] = max(dp[i-1][1], dp[i-2][0], dp[i-2][1]) + nums[i]
    dp[i][1] = max(dp[i-2][0], dp[i-2][1]) + nums[i]

print(max(dp[N-1]))