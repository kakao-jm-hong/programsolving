var min = 9;

function DFS(N, number, count, result) {
    if(min <= count) return;

    if(result===number) {
        min = min > count ? count : min;
        return;
    }

    if(count===8) return;

    let rest = 8 - count;
    let nn = 0;
    for(let i=0;i<rest;i++){
        nn = nn * 10 + N;
        DFS(N, number, count+1+i, result+nn);
        DFS(N, number, count+1+i, result-nn);
        DFS(N, number, count+1+i, result*nn);
        DFS(N, number, count+1+i, result/nn);
    }

}

function solution(N, number) {
    
    var answer = 0;
    DFS(N, number, 0, 0);
    if(min> 8)
        answer = -1;
    else
        answer = min;

    return answer;
}
