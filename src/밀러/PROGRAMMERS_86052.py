from typing import List


def solution(grid: List[str]) -> List[int]:
    row: int = len(grid)
    col: int = len(grid[0])

    space: List[List[List[bool]]] = [[[False for _ in range(4)]
                                      for _ in range(col)] for _ in range(row)]

    dr = [-1, 0, 1, 0]  # [north, east, south, west]
    dc = [0, 1, 0, -1]

    result: List[int] = []
    for i in range(len(space)):
        for j in range(len(space[0])):
            for k in range(4):
                if space[i][j][k]:
                    continue

                r, c, d = i, j, k
                count: int = 0
                while True:
                    if grid[r][c] == "S":
                        r = (r + dr[(d + 0) % 4]) % row
                        c = (c + dc[(d + 0) % 4]) % col
                    elif grid[r][c] == "L":
                        r = (r + dr[(d + 1) % 4]) % row
                        c = (c + dc[(d + 1) % 4]) % col
                        d = (d + 1) % 4
                    else:
                        r = (r + dr[(d - 1) % 4]) % row
                        c = (c + dc[(d - 1) % 4]) % col
                        d = (d - 1) % 4

                    if space[r][c][d]:
                        break

                    space[r][c][d] = True
                    count += 1

                result.append(count)

    return sorted(result)


if __name__ == "__main__":
    print(solution(["SL", "LR"]))
    print(solution(["S"]))
    print(solution(["R", "R"]))
