import sys;input=sys.stdin.readline
N,M=map(int,input().split())
A=list(map(int,input().split()))
left,right=0,0

s=A[0]
ans=0
while right<N:
    if s<M:
        right+=1
        if right!=N:
            s+=A[right]
    elif s==M:
        ans+=1
        s-=A[left]
        left+=1

    else: #s>M
        s-=A[left]
        left+=1

print(ans)


