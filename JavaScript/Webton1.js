// 브루드 포스 문제
var answer;
var rates_arr;
var size;

var dp;

function solution(k, rates) {
    rates_arr = rates;
    size = rates.length;
    answer = k;

    // 초기화
    dp = Array(size).from({length: size},() => Array(2).fill(-1));

    // 현재 자본, 현재 달러 소지 여부, index
    amswer = DFS(k, 0, 0);

    return answer;
}

function DFS(k, has_item, index) {
    if(index === size) {
        return 0;
    }
    
    let cur_Max = 0;

    // 달러를 소지한 경우
    if(has_item === 1){
        // 파는 경우
        cur_Max = Math.max(cur_Max,DFS(k + rates_arr[index],0,index+1));
        // 팔지 않는 경우
        cur_Max = Math.max(cur_Max,DFS(k,1,index+1));
    }
    // 달러를 소지하지 않는 경우
    else {
        // 사는 경우 && 소지금이 더많아야 한다.
        if(k >= rates_arr[index]){
            DFS(k - rates_arr[index],1,index + 1);
        }        
        // 사지 않는 경우
        DFS(k,0,index+1);
    }
    return;
}


var k = 1500;
var t_rates = [];
for(let i=0;i<100000;i++){
    let v = Math.floor(Math.random() * 10001);
    console.log(v);
    t_rates.push(v);
}
console.log(typeof(t_rates[0]));

var test = solution(k,t_rates);
console.log(test);