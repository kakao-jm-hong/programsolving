
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
74
75
76
77
78
79
80
81
82
83
84
85
86
87
88
89
90
91
92
93
94
95
96
97
98
99
100
101
102
103
104
105
106
107
108
109
110
111
112
113
114
115
116
117
118
119
120
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

// TODO 존나 귀찮으니 이래짠다~
const CheckComp = (stand, comp) => {
    let N = comp.length;
    for(let i=0;i<N;i++) {
        let check = false;
        for(let j=0;j<N;j++){
            if(stand[i][0] === comp[j][0] && stand[i][1] === comp[j][1]) {
                check = true;
                break;
            }
        }
        if(!check)
            return false;
    }
    return true;
};

const checkCounting = (stand, comp) => {
    if(stand.length !== comp.length )
        return false;

    let MaxY = -1;
    let MaxX = -1;

    comp.forEach((arr) => {
        MaxY = Math.max(MaxY,arr[0]);
        MaxX = Math.max(MaxX,arr[1]);
    });
    for(let d=0;d<4;d++) {
        if(CheckComp(stand, comp)){
            return true;
        }

        // SWAP
        comp = comp.map((arr) => [arr[1],MaxY-arr[0]]);
        [MaxY, MaxX] = [MaxX, MaxY];
    }

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
               count+= arr.length;
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
