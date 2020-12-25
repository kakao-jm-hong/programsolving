package excercise;

public class 등굣길 {
    static int dp[][];
    static boolean map[][];
    public int solution(int m, int n, int[][] puddles) {
        dp = new int[n+1][m+1];
        map = new boolean[n+1][m+1];
        for(int i=0;i<puddles.length;i++){
            int x = puddles[i][0];
            int y = puddles[i][1];
            map[y][x] = true;
        }
        dp[0][1] =1;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(map[i][j]){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = (dp[i-1][j] + dp[i][j-1])%1000000007;
                }
            }
        }
        return dp[n][m];
    }
}
