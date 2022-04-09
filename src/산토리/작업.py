from collections import defaultdict

N = int(input())

# 선행 작업들의 번호가 해당 작업보다 아래밖에 없다
# 즉, 그냥 순서대로 받으면서 선행 작업 중 가장 오래 걸렸던 작업의 시간을 더해준다
# 이 때, 각 작업이 완료되는 시각을 리스트에 저장해둔다

done_time = [0 for _ in range(N+1)]

for i in range(N):
    task = list(map(int, input().split()))
    time = task[0]
    cnt = task[1]
    if cnt == 0:
        done_time[i+1] = time
    else:
        longest_task = 0
        for pre_task in task[2:]:
            longest_task = max(longest_task, done_time[pre_task])
        done_time[i+1] += time
        done_time[i+1] += longest_task

print(max(done_time))


