from copy import deepcopy
N = int(input())

nums = list(map(int, input().split()))

operators = list(map(int, input().split())) # 덧셈, 뺄셈, 곱셈, 나눗셈

# 최대 10!/3!3!3! 의 가능성

# 재귀를 하면서 operator가 없을 때까지 반복?

results = []


def calculate(num, cnt, op_arr):
    if cnt == N:
        results.append(num)
        return

    # 같은 것이 있는 순열 처리가 제대로 된다
    for i, operator in enumerate(op_arr):
        if operator > 0:
            new_operator = deepcopy(op_arr)
            new_operator[i] -= 1
            if i == 0:
                calculate(num+nums[cnt], cnt+1, new_operator)
            elif i == 1:
                calculate(num-nums[cnt], cnt+1, new_operator)
            elif i == 2:
                calculate(num*nums[cnt], cnt+1, new_operator)
            else:
                if num > 0:
                    new_num = num//nums[cnt]
                else:
                    new_num = -((-num)//nums[cnt])
                calculate(new_num, cnt+1, new_operator)


calculate(nums[0], 1, operators)
print(max(results))
print(min(results))