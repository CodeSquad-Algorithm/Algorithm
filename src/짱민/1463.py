import sys;input=sys.stdin.readline
from collections import deque
N=int(input())

dp = [sys.maxsize] * (N+1)


def dfs():
    Q=deque()
    Q.append(N)
    while Q:
        pointer=Q.popleft()

        if pointer%3 == 0 and checkRange(pointer//3) :
            if dp[pointer//3] >= dp[pointer]+1:
                dp[pointer//3] = dp[pointer] + 1
                Q.append(pointer//3)

        if pointer%2 == 0 and checkRange(pointer//2):
            if dp[pointer//2] >= dp[pointer] + 1:
                dp[pointer//2] = dp[pointer] + 1
                Q.append(pointer//2)

        if checkRange(pointer-1):
            if dp[pointer-1] >= dp[pointer] + 1:
                dp[pointer-1] = dp[pointer] + 1
                Q.append(pointer-1)
    
def checkRange(pointer):
    if 1 <= pointer < N:
        return True
    return False

dp[N]=0
dfs()
print(dp[1])
