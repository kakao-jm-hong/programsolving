function solution(land, height) {
    var answer = 0;
    var len = land.length;
    var color = Array.from({length: len}, () => Array(len).fill(0));
    
    let c = 0;
    for(let i=0;i<len;i++) {
        for(let j=0;j<len;j++) {
            if(color[i][j] !== 0)
                continue;
            ++c;
            Proccess(color,land,i,j,c,len,height);
        }
    }

    var arr =[];
    for(let i=0;i<len;i++) {
        for(let j=0;j<len;j++) {
            for(let d=0;d<4;d++) {
                let ny = dy[d] + i;
                let nx = dx[d] + j;

                if(ny<0||nx<0||ny>=len||nx>=len)
                    continue;

                if(color[i][j] === color[ny][nx])
                    continue;

                let a = color[i][j];
                let b = color[ny][nx];
                let distance = Math.abs(land[i][j] - land[ny][nx]);
                arr.push({a:a,b:b,w:distance});
            }
        }
    }

    arr.sort(function(a, b){
        return a.w - b.w;
    })

    var cnt = 0;
    for(let i=0;i<arr.length&&cnt<c-1;i++){
        if(Union(arr[i].a,arr[i].b)){
            cnt++;
            answer+=arr[i].w;
        }
    }
    
    return answer;
}

function Proccess(color,land,y,x,c,len,height) {
    const q = new Queue();
    color[y][x] = c;

    q.push({y: y,x: x});

    while(!q.isEmpty()) {
        let cur = q.pop();
        
        for(let d=0;d<4;d++) {
            let ny = dy[d] + cur.y;
            let nx = dx[d] + cur.x;

            if(ny<0||nx<0||ny>=len||nx>=len)
                continue;
            if(color[ny][nx] !== 0)
                continue;
            const diff = Math.abs(land[cur.y][cur.x] - land[ny][nx]);
            if(diff>height)
                continue;
            q.push({y: ny,x: nx});
            color[ny][nx] = c;
        }
    }
}

var parrent = Array.from({length: 100001}, (_,index)=> index);

function Find(x) {
    if(parrent[x] === x){
        return x;
    }

    const xx = Find(parrent[x]);
    return parrent[x] = xx;
}

function Union(x, y) {
    const xx = Find(x);
    const yy = Find(y);
    if(xx === yy)
        return false;
    if(xx < yy)
        parrent[yy] = xx;
    else
        parrent[xx] = yy;
    return true;
}

class Queue {
    constructor() {
        this.queue = [];
        this.offset = 0;
    }

    isEmpty() {
        return (this.queue.length === 0);
    }

    push(item) {
        this.queue.push(item);
    }

    pop() {
        if (this.queue.length === 0) return undefined;

        const item = this.queue[this.offset];
        if (++ this.offset * 2 >= this.queue.length){
            this.queue  = this.queue.slice(this.offset);
            this.offset = 0;
        }

        return item;
    }
}

var dy = [0,1,-1,0];
var dx = [1,0,0,-1];

console.log(solution([[10, 11, 10, 11], [2, 21, 20, 10], [1, 20, 21, 11], [2, 1, 2, 1]],1));