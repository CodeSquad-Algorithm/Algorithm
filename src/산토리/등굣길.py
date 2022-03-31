def solution(m, n, puddles):
    answer = 0
    
    dp = [[1 for _ in range(m)] for _ in range(n)]
    
    
    for r in range(n):
        for c in range(m):
            if [c+1, r+1] in puddles:
                dp[r][c] = 0
                continue
            if r == 0:
                dp[r][c] = dp[r][c-1]
                continue
            if c == 0:
                dp[r][c] = dp[r-1][c]
                continue
            
            dp[r][c] = (dp[r-1][c] + dp[r][c-1])%1000000007

    answer = dp[n-1][m-1]


    return answer