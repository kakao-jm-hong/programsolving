var answer;

function solution(begin, target, words) {
    answer = 987654321;
    var len = words.length;
    var Edge = Array(len);
    var map = new Map();
    var targetNum;

    for(let i=0; i<len; i++) {
        map.set(words[i],i);
    }

    for(let i=0; i<len; i++) {
        Edge[i] = [];

        if(target === words[i])
            targetNum = i;
    }

    for(let i=0; i<len-1; i++){
        for(let j=i+1; j<len;j++){
            if(diffCount(words[i],words[j]) === 1){
                let a = map.get(words[i]);
                let b = map.get(words[j]);
                Edge[a].push(b);
                Edge[b].push(a);
            }
        }
    }

    var visit = Array(len);
    
    for(let i=0; i<len; i++){
        if(diffCount(begin, words[i]) === 1){
            visit[i] = true;
            DFS(Edge, visit, i, targetNum, 1);
            visit[i] = false;
        }
    }

    if(answer === 987654321)
        return 0;
    return answer;
}

function DFS(Edge, visit, cur, target, count) {
    if(cur === target) {
        answer = Math.min(answer, count);
        return;
    }

    let len = Edge[cur].length;
    for(let i=0;i<len;i++){
        let next = Edge[cur][i];
        if(visit[next])
            continue;
        visit[next] = true;

        DFS(Edge, visit, next, target, count+1);

        visit[next] = false;
    }
}

function diffCount(a, b) {
    let len = a.length;
    let count = 0;
    for(let i = 0; i< len; i++){
        if(a.charAt(i) !== b.charAt(i)){
            count++;
        }
    }
    return count;
}

