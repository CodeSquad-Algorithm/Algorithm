s1 = input()
s2 = input()

l1, l2 = len(s1), len(s2)
answer = 0
dp = [[0 for _ in range(l2)] for _ in range(l1)]

for i in range(l1):
    for j in range(l2):
        
        if s1[i] == s2[j]:
            dp[i][j] = dp[i-1][j-1] + 1
            answer = max(answer, dp[i][j])

print(answer)