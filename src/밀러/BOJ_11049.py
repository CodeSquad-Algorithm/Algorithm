import sys
from typing import List, Tuple, Optional


def solution_fail(n: int, matrices: List[List[int]]) -> int:
    dp: List[List[int]] = [[0 for y in range(n)] for x in range(n)]

    for i in range(1, n + 1):
        for j in range(n - i):
            r, c = j, j + i

            left: int = dp[r][c - 1] + matrices[r][0] * matrices[c][0] * matrices[c][1]
            right: int = matrices[r][0] * matrices[r][1] * matrices[c][1] + dp[r + 1][c]

            dp[r][c] = min(left, right)

    return dp[0][n - 1]


def solution_success(n: int, matrices: List[List[int]]) -> int:
    dp: List[List[int]] = [[0 for y in range(n)] for x in range(n)]

    for i in range(1, n + 1):
        for j in range(n - i):
            r, c = j, j + i

            if i == 1:
                dp[r][c] = matrices[r][0] * matrices[r][1] * matrices[c][1]
                continue

            dp[r][c] = sys.maxsize
            for k in range(r, c):
                dp[r][c] = min(dp[r][c], dp[r][k] + dp[k + 1][c]
                               + matrices[r][0] * matrices[k][1] * matrices[c][1])

    return dp[0][n - 1]


if __name__ == "__main__":
    N: int = int(input())
    arr: List[List[int]] = []

    for _ in range(N):
        arr.append(list(map(int, input().split())))
    print(solution_success(N, arr))
