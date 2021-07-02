var answer= 0;
var check;
var map;
var len;
var pre;
var isPossible= [false,false,true,true,false,true,true,false,true,false,false,false];
var nonCheckX =[-1,-1,0,1,-1,0,2,-1,1,-1,-1,-1];
var rectangle= [
                    [[1,1,1],
                     [0,0,1],
                     [0,0,0]],        
                    [[1,1,0],
                     [1,0,0],
                     [1,0,0]],
                    [[1,0,0],
                     [1,1,1],
                     [0,0,0]],
                    [[0,1,0],
                     [0,1,0],
                     [1,1,0]],
                    [[1,1,1],
                     [1,0,0],
                     [0,0,0]],
                    [[1,0,0],
                     [1,0,0],
                     [1,1,0]],
                    [[0,0,1],
                     [1,1,1],
                     [0,0,0]],
                    [[1,1,0],
                     [0,1,0],
                     [0,1,0]],
                    [[0,1,0],
                     [1,1,1],
                     [0,0,0]],
                    [[0,1,0],
                     [0,1,1],
                     [0,1,0]],
                    [[1,1,1],
                     [0,1,0],
                     [0,0,0]],
                    [[0,1,0],
                     [1,1,0],
                     [0,1,0]]
                ];
function solution(board) {
    map = board;
    len = board.length;
    check = Array.from({length: len},() => Array(len).fill(false));
    
    for(let i=0;i<len;i++){
        if(pre)
            i =0;
        pre = false;
        for(let j=0;j<len;j++)
            if(map[i][j]!==0)
                Process(i,j);
    }
    
    map.map((i)=>console.log(i));
    
    return answer;
}

function Process(i, j) {
    // 앞전에 조사해서 없애는 것이 불가능한 도형     
    if(check[i][j])
        return;
    
    // 도형 list 이때 map은 0으로 초기화 된다.
    let {list, color} = getRectangle(i,j);
    
    // list로 맵을 반환한다.
    let {m, minX} = getMap(list);
    let {m_check, nonX} = Marking(m, minX);
    if(m_check){
        if(!UpCheck(list,nonX)){
            for(let l of list) {
                map[l[0]][l[1]] = color;
            }
        }else {
            pre = true;
            answer++;
        }
    }else{
        for(let l of list) {
            map[l[0]][l[1]] = color;
            check[l[0]][l[1]] = true;
        }
    }
}

function UpCheck(list,nonX) {
    // 위에 아무것도 없으면 true
    for(let l of list) {
        if(l[1]===nonX)
            continue;
        let y = l[0];
        while(--y >= 0) {
            if(map[y][l[1]] !== 0) {
                return false;
            }
        }
    }
    
    return true;
    
}

function Marking(m, minX) {
    let num;
    
    for(let n=0;n<12;n++) {
        let p = ((m1,m2) => {
                for(let i=0;i<3;i++){
                    for(let j=0;j<3;j++){
                        if(m1[i][j] !== m2[i][j])
                            return false;
                    }
                }
                return true;
            })(m,rectangle[n]);
        
        if(p) {
            num = n;
            break;
        }
    }
    let nonX = nonCheckX[num] !== -1 ? minX + nonCheckX[num] : -1;
    return {m_check: isPossible[num], nonX};
}


const INF = 987654321;
function getMap(list) {
    
    let minY = INF;
    let minX = INF;
    for(let l of list) {
        minX = Math.min(minX, l[1]);
        minY = Math.min(minY, l[0]);
    }
    
    let m = Array.from({length: 3}, () => Array(3).fill(0));
    
    for(let l of list) {
        m[l[0] - minY][l[1] - minX] = 1;
    }
    
 
    return {m, minX};
}

const dy = [1,-1,0,0];
const dx = [0,0,-1,1];

function getRectangle(i,j) {
    let color = map[i][j];
    let list = [];
    
    let q = new Queue();
    q.push([i,j]);
    map[i][j] = 0;
    while(!q.isEmpty()){
        let cur = q.pop();
        list.push(cur);
        for(let d=0;d<4;d++) {
            let ny = dy[d] + cur[0];
            let nx = dx[d] + cur[1];
            
            if(ny<0||nx<0||ny>=len||nx>=len)
                continue;
            
            if(map[ny][nx]!==color)
                continue;
            map[ny][nx] = 0;
            q.push([ny,nx]);
        }
    }
    
    return {list, color};
    
}

class Queue {
    constructor() {
        this._arr = [];
    }
    
    push(v) {
        this._arr.push(v);
    }
    
    pop() {
        return this._arr.shift();
    }
    
    isEmpty() {
        return this._arr.length === 0;
    }
}