import sys;input=sys.stdin.readline
import bisect
N=int(input())
jibang=list(map(int,input().split()))
M=int(input())
psum=[ 0 for _ in range(N+1)]
jibang.sort()

s=sum(jibang)
if s < M:
    print(jibang[-1])
    sys.exit()

for i in range(1,N+1):
    psum[i]=psum[i-1]+jibang[i-1]

left,right=0,jibang[-1]
i=0
while left<=right:
    mid=(left+right)//2

    point=bisect.bisect_left(jibang,mid)
    t_sum=psum[point] + mid * (N-point)

    if t_sum>M:
        right=mid-1
    else:
        left=mid+1
            
print(right)
