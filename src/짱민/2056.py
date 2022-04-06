import sys;input=sys.stdin.readline

N=int(input())
work=[[] for _ in range(N+1)]
time=[0 for _ in range(N+1)]
visit=[False for _ in range(N+1)]

for i in range(1,N+1):
    a=list(map(int,input().split()))
    time[i]=a[0]
    num=a[1]
    for j in range(2,num+2):
        work[i].append(a[j])

for i in range(1, N+1):
    tmp = 0
    for j in work[i]:
        tmp = max(tmp, time[j])
    time[i] += tmp
print(max(time))
