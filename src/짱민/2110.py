import sys;input=sys.stdin.readline

N,C=map(int,input().split())
house=[ 0 for _ in range(N) ]
for i in range(N):
    house[i]=int(input())
house.sort()
print(house)

left=1
right=house[-1]-house[0]
answer=0
while left<=right:
    mid=(left+right)//2
    print(mid,'mm')
    cur=house[0]
    D=C-1
    for i in range(1,N):
        print(house[i]-cur,'--')
        if house[i]-cur>=mid:
            cur=house[i]
            print(cur,'asdf')
            D-=1
    print(D,'DDD')
    if D<=0:
        answer=mid
        left=mid+1
    else:
        right=mid-1

print(answer)

# 1 2 4 8 9
