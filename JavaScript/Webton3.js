// 시뮬레이션

// board 블록
const block_arr = [
    [[1],[1],[1]],
    [1,1,1],
    [[1,0],[1,1]],
    [[0,1],[1,1]],
    [[1,1],[0,1]],
    [[1,1],[1,0]],
]

function solution(block,board) {
    var b = block_arr[block];
    
    var answer = GetMaxValue(b, board);

    return answer;
}

// [D] 블록 좌측 위를 기준으로 찾는다.
function GetMaxValue(block, board) {
    var N = board.length;
    var maxCnt = 0;
    for(let i=0;i<N;i++) {
        for(let j=0;j<N;j++){
            // 놔둘수없음
            if(board[i][j] === 1)
                continue;
            // 놔둘수있음
            if(Check(i,j,block, board, N)){
                var cnt = getCnt(i,j,block,board,N);
                maxCnt = Math.max(maxCnt, cnt);
            }
        }
    }

    return maxCnt;
}

function getCnt(i,j,block,board,N) {
    var cnt = 0;
    var row = block.length;
    var col = block[0].length;
    var copyMap = Array.from({length: N},()=>Array(N));

    for(let ii=0;ii<N;ii++){
        for(let jj=0;jj<N;jj++){
            copyMap[ii][jj] = board[ii][jj];
        }
    }

    for(let ii=0;ii<row;ii++){
        for(let jj=0;jj<col;jj++){
            copyMap[ii+i][jj+j] = board[ii][jj];
        }
    }

    const isFull = (v) => v === 1;

    // 하나의 자리가 1일 경우 ++
    for(let ii=0;ii<N;ii++){
        cnt+= copyMap[ii].every(isFull) ? 1 : 0;
    }
    
    return cnt;
}

function Check(i,j,block, board, N) {
    var row = block.length;
    var col = block[0].length;
    
    if(i + row -1 >= N)
        return false;
    if(j + col -1 >= N)
        return false;
    
    for(let ii=i;ii<i + row;ii++) {
        for(let jj=j;jj<j + col;jj++) {
            if(block[ii-i][jj-j]=== 1 && board[ii][jj]===1)
                return false;
        }
    }

    return true;
}


var test = solution(0,[[1,0,0,0],[1,0,0,1],[1,1,0,1],[1,1,0,1]]);
console.log(test);