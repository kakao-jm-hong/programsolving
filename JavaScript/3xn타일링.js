function solution(n) {
    var dp = Array(n+1);
    dp[0] = 1;
    dp[2] = 3;
    var tmp = 0;
    for(let i=2;i<=n;i+=2) {
        dp[i] = ((dp[i-2] * 3) % 1000000007 + tmp * 2 % 1000000007) % 1000000007;
        tmp += dp[i-2];
    }
    return dp[n];
}

console.log(solution(6));