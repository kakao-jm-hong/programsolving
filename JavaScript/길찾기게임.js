const hash = new Map();
const set = new Set();

const preOrder = (x, y, level, maxLevel, arrSet, pre, start, end) => {
    pre.push(hash.get(x+","+y));
    if(level + 1 >= maxLevel) return;
    
    const nextY = arrSet[level + 1];
    
    // 왼쪽
    for(let j=start;j<x;j++) {
        if(hash.has(j+","+nextY)) {
            preOrder(j, nextY, level+1, maxLevel, arrSet, pre, start, x-1);
            break;
        }
    }
    
    //오른쪽
    for( let j=x+1;j<=end;j++) {
        if(hash.has(j+","+nextY)) {
            preOrder(j, nextY, level+1, maxLevel, arrSet, pre, x+1, end)
            break;
        }
    }
};

const postOrder = (x, y, level, maxLevel, arrSet, post, start, end) => {
    if(level + 1 >= maxLevel) return;
    
    const nextY = arrSet[level + 1];
    
    // 왼쪽
    for(let j=start;j<x;j++) {
        if(hash.has(j+","+nextY)) {
            postOrder(j, nextY, level+1, maxLevel, arrSet, post, start, x-1);
            break;
        }
    }
    
    //오른쪽
    for( let j=x+1;j<=end;j++) {
        if(hash.has(j+","+nextY)) {
            postOrder(j, nextY, level+1, maxLevel, arrSet, post, x+1, end)
            break;
        }
    }
    
    post.push(hash.get(x+","+y));
};

function solution(nodeinfo) {
    let cnt = 1;
    let startY = 0;
    let startX = 0;
    
    let end = 0;
    let start = 10001;
    
    for(let node of nodeinfo) {
        const [x, y] = node;
        hash.set(x+","+y, cnt++);
        end = Math.max(end,x);
        start = Math.min(start,x);
        set.add(y);
        
        if(startY < y) {
            startY = y;
            startX = x;
        }
    }
    
    const arrSet = Array.from(set).sort((a,b) => (b - a));
    const maxLevel = arrSet.lengh;
    
    const pre = [];
    const post = [];
    
    preOrder(startX,startY,0,maxLevel,arrSet,pre,start,end);
    postOrder(startX,startY,0,maxLevel,arrSet,post,start,end);
    
    return [pre, post];
}