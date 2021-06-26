var max_dp = Array(101);
var min_dp = Array(101);

for(let i=0;i<101;i++){
    max_dp[i] = Array(101);
    min_dp[i] = Array(101);
}

function solution(arr) {
    var answer;
    var num_size = (arr.length + 1) / 2;
    
    for(let i=0;i<arr.length;i+=2){
        max_dp[i/2][i/2] = parseInt(arr[i]);
        min_dp[i/2][i/2] = parseInt(arr[i]);
    }
    
    for(let len=1;len < num_size;len++){
        for(let i = 0;i + len <num_size;i++){
            let j = i + len;
            max_dp[i][j] = -987654321;
            min_dp[i][j] = 987654321;
            
            for(let k=0; k < len;k++){
                var sign = arr[(i+k) * 2 + 1];
                if(sign === "+"){
                    max_dp[i][j] = Math.max(max_dp[i][j],max_dp[i][i+k] + max_dp[i+k+1][j]);
                    min_dp[i][j] = Math.min(min_dp[i][j],min_dp[i][i+k] + min_dp[i+k+1][j]);
                } else if(sign === "-"){
                    max_dp[i][j] = Math.max(max_dp[i][j],max_dp[i][i+k] - min_dp[i+k+1][j]);
                    min_dp[i][j] = Math.min(min_dp[i][j],min_dp[i][i+k] - max_dp[i+k+1][j]);
                }
            }
        }
    }
    
    return max_dp[0][num_size-1];
}