// 트리의 지름을 구하는 방법은 그리디를 이용해서 풀수가 있다.
// 1. 임의의 노드 a 에서 가장 깊은 노드 b를 DFS or BFS를 이용해서 구한다.
// 2. b노드에서 가장깊은 노드 c를 찾으면  b-c가 트리의 지름이 된다.

class Queue {
    constructor() {
        this._q_size = 250000;
        this._arr = Array(250000);
        this.front = 0;
        this.rear = 0;
    }
    
    push(value) {
        this.rear = (this.rear + 1) % this._q_size;
        this._arr[this.rear] = value;
    }
    
    pop() {
        this.front = (this.front + 1) % this._q_size;
        return this._arr[this.front];
    }

    isEmpty() {
        return this.front === this.rear;
    }

    size() {
        return (this._q_size + this.rear - this.front) % this._q_size;
    }
}

// 한지점에서 트리의 지름과 같은 노드가 2개이상이면 그값이 max
// 아니면 -1 값이 정답.

function solution(n, edges) {
    // init Edge 작업
    // var Edge = Array(n + 1).fill().map(_ => []);
    var Edge = Array.from({length : n + 1},() => [])

    const size = edges.length;
    
    for(let i=0;i<size;i++) {
        const edge = edges[i];
        let a = parseInt(edge[0]);
        let b = parseInt(edge[1]);
        Edge[a].push(b);
        Edge[b].push(a);
    }
    
    // 정점 후보 노드
    let [index, count, distance] = FallNode(1, Edge, n);
    
    [index, count, distance] = FallNode(index, Edge, n);
    
    if(count >= 2)
        return distance;
    
    [index, count, distance] = FallNode(index, Edge, n);
    
    return count === 1 ? distance - 1 : distance;
    
}

function FallNode(node, Edge, n) {
    var q = new Queue();

    var visit = Array(n+1).fill(false);
    q.push(node);
    visit[node] = true;

    let distance = -1;
    let count = 0;
    let index;
    
    while(!q.isEmpty()) {
        ++distance;
        count = 0;
        let size = q.size();
        for(let i=0;i<size;i++) {
            var check = true;
            let cur = q.pop();
            let len = Edge[cur].length;
            for(let i=0;i<len;i++){
                let next = Edge[cur][i];
                if(visit[next])
                    continue;
                check = false;
                q.push(next);
                visit[next] = true;
            }
            
            if(check) {
                index = cur;
                count++;
            }
        }

    }
    return [index, count, distance];
}

var arr= [[1,2],[2,3],[3,4]];
console.log(solution(4,arr));