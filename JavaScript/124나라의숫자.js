var ans = [4, 1, 2];

function solution(n) {
    var answer = '';

    while (n > 0) {
        let rem = n % 3;
        n = Math.floor(((n - 1) / 3));
        answer = ans[rem] + answer;
    }

    return answer;
}

console.log(solution(4));