// 투포인터 문제

function solution(money, cost) {
    var answer = 0;
    var sum = 0;
    var front = 0;
    var rear = 0;
    var N = cost.length;
    var cnt = 0;

    while(true) {
        if(sum>money){
            sum -= cost[front++];
            cnt--;
        }else if(rear == N) break;
        else {
            sum += cost[rear++];
            cnt++;
        }
        if(sum <= money)
            answer = Math.max(answer,cnt);
    }

    return answer;
}