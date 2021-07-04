//그리디 문제 증가하는 수열

function solution(k, rates) {
    var initK = k;
    var sum = 0;
    var len = rates.length;
    var check = false;
    var preValue;
    for(let i=0;i<len;i++) {
        // 구입 햇을 경우
        if(check){
            let ni = i;
            let pre = rates[i];
            while(++ni < len) {
                if(pre > rates[ni]){
                    check =false;
                    i = ni -1;
                    k+= rates[i];
                    break;
                }
                pre = rates[ni];
            }
            sum += pre - preValue; 
        }
        // 구입 X
        else {
            if(k < rates[i])
                continue;
            preValue = rates[i];
            let ni = i;
            while(++ni < len) {
                if(preValue < rates[ni]){
                    i = ni -1;
                    check = true;
                    k -= rates[i];
                    break;
                }
                preValue = rates[ni];
            }
        }
    }

    return initK + sum;
}

var t_k = 1000;
var t_rates = [];
for(let i=0;i<10;i++){
    t_rates.push(Math.floor(Math.random()*1501));
}

console.log(t_k, t_rates);

var test = solution(t_k,t_rates);
console.log(test);