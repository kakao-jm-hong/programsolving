var dy = [1, 0, -1];
var dx = [0, 1, -1];

function solution(n) {
    var arr = new Array(n);
    for (let i = 0; i < n; i++) {
        arr[i] = new Array(i + 1);
    }

    var len = n;
    var d = 0;
    var y = -1;
    var x = 0;
    var cnt = 0;
    while (len != 0) {
        for (let i = 0; i < len; i++) {
            y = y + dy[d];
            x = x + dx[d];
            arr[y][x] = ++cnt;
        }
        len--;
        d = (d + 1) % 3;
    }

    var answer = [];

    for (let i = 0; i < arr.length; i++) {
        for (let j = 0; j < arr[i].length; j++) {
            answer.push(arr[i][j]);
        }
    }

    return answer;
}

console.log(solution(4));

console.log(solution(4));