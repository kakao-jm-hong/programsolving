var fac = Array(21);

function solution(n, k) {
    fac[0] = 0;
    fac[1] = 1;
    for(let i =2;i<=20;i++){
        fac[i] = fac[i-1]*i;
    }

    var answer = Array(n);
    var ansCnt = 0;
    var check = Array(n+1);

    var pivot = n-1;
    var count = 0;

    while(ansCnt != n) {
        for(let i=1;i<=n;i++){
            if(check[i])
                continue;
            if(ansCnt == n-1) {
                answer[ansCnt++] = i;
                break;
            }
            
            if(fac[pivot] + count >= k) {
                answer[ansCnt++] = i;
                check[i] = true;
                pivot--;
                break;
            }

            count += fac[pivot];
        }
    }

    return answer;
}