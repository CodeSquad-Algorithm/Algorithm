from typing import List


def solution(N: int, M: int, budgets: List[int]) -> int:

    if sum(budgets) <= M:
        return max(budgets)

    left, right = 1, 100_000

    mid: int = 0
    while left <= right:
        mid = (left + right) // 2

        total: int = 0
        count: int = 0
        for budget in budgets:
            if budget < mid:
                total += budget
            else:
                count += 1
                total += mid

        if M - count < total <= M:
            return mid
        elif total > M:
            right = mid - 1
        else:
            left = mid + 1

    return mid


if __name__ == "__main__":
    N: int = int(input())
    budgets: List[int] = list(map(int, input().split()))
    M: int = int(input())

    print(solution(N, M, budgets))
