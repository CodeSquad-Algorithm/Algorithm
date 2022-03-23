import sys;input=sys.stdin.readline
from itertools import permutations

N=int(input())
A=list(map(int,input().split()))
op=list(map(int,input().split())) #  [ + - * / ]
ops=""
for i in range(4):
    if i==0:
        for i in range(op[i]):
            ops+="+"
    elif i==1:
        for i in range(op[i]):
            ops+="-"
    elif i==2:
        for i in range(op[i]):
            ops+="*"
    else:
        for i in range(op[i]):
            ops+="/"

p=set(permutations(ops,N-1))

maxAns=-sys.maxsize
minAns=sys.maxsize

for i in p:
    temp=A[0]
    for j in range(0,N-1):
        if i[j]=="+":
            temp=temp+A[j+1]
        elif i[j]=="-":
            temp=temp-A[j+1]
        elif i[j]=="*":
            temp=temp*A[j+1]
        else:
            if temp<0:
                temp=-(abs(temp)//A[j+1])
            else:
                temp=temp//A[j+1]
    maxAns=max(temp,maxAns)
    minAns=min(temp,minAns)


print(maxAns)
print(minAns)
