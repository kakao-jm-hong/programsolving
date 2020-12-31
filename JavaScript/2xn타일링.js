var dp = new Array(60001);
function solution(n) {
    dp[0] = 1;
    dp[1] = 1;
    dp[2] = 2;
    for (let i = 3; i <= 60000; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n] % 1,000,000,007;
}