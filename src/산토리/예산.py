# 정해진 총액 이하에서 가능한 한 최대의 총 예산을 배정한다
# 모든 요청이 배정될 수 있는 경우에는 요청한 금액을 그대로
# 모든 요청이 배정될 수 없는 경우에는 특정한 정수 상한액을 계산하여, 그 이상인 예산요청에는 모두 상한액을 배정한다. 상한액 이하 예산요청에 대해서는 요청한 금액을 그대로 배정한다.

def get_answer():
    min_req, max_req = min(req), max(req)
    
    if total >= sum(req):
        return max_req

    avg = total // N
    print("avg:",avg)
    if min_req >= avg:
        return avg

    limit = avg
    while True:
        remain = total
        cnt = 0
        for r in req:
            if r <= limit:
                remain -= r
            else:
                remain -= limit
                cnt += 1
        if remain >= cnt:
            limit += remain // cnt
        else:
            return limit 
    

N = int(input())
req = list(map(int, input().split()))
total = int(input())

print(get_answer())

