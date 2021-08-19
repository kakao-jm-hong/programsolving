const dy = [1,-1,0,0];
const dx = [0,0,1,-1];

const Mapping = (list) => {
    let minY = Number.MAX_VALUE;
    let minX = Number.MAX_VALUE;
    for(let arr of list) {
      minY = Math.min(minY, arr[0]);
      minX = Math.min(minX, arr[1]);
    }
    
    return list.map((arr)=> [arr[0]-minY,arr[1]-minX]);
    
};

const BFS = (visit, table, y, x, N, stand) => {
    const q = [];
    const list = [];
    q.push([y,x]);
    visit[y][x] = true;
    while(q.length !== 0) {
        let [i, j] = q.shift();
        list.push([i,j]);
        
        for(let d=0; d<4; d++) {
            let ny = i + dy[d];
            let nx = j + dx[d];

            if(ny<0||nx<0||ny>=N||nx>=N)
                continue;
            if(visit[ny][nx])
                continue;
            if(table[ny][nx] === stand)
                continue;
            visit[ny][nx] = true;
            q.push([ny,nx]);
        }
    }
    return Mapping(list);
};

const TableInit = (table, stand) => {
    let N = table.length;
    let list = [];
    let visit = Array.from({length: N}, () => Array(N).fill(false));
    
    for(let i=0; i<N; i++) {
        for(let j=0;j<N; j++) {
            if(visit[i][j])
                continue;
            if(table[i][j] === stand)
                continue;
            list.push(BFS(visit, table, i, j, N, stand));
        }
    }
    
    return list;
};

const checkCounting = (stand, comp) => {
    return false;
};

const getMaxCount = (gameList, tableList) => {
    const N = tableList.length;
    const check = Array(N).fill(false);
    let count = 0;
    
    gameList.forEach((arr)=> {
       for(let i=0;i<N;i++){
           if(check[i])
               continue;
           if(checkCounting(arr,tableList[i])){
               check[i] = true;
               count++;
               break;
           }
       } 
    });
    
    return count;
};

function solution(game_board, table) {
    
    const tableList = TableInit(table, 0);
    const gameList = TableInit(game_board, 1);
    return getMaxCount(gameList, tableList);
}
