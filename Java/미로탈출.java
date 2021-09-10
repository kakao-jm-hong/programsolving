import java.util.*;

class Solution {
    static int[] dy = {1,-1,0,0};
    static int[] dx = {0,0,1,-1};
    
    static class Map implements Comparable<Map> {
        int y;
        int x;
        int d;
        int w;
        
        Map(int y,int x,int d, int w) {
            this.y = y;
            this.x = x;
            this.d = d;
            this.w = w;
        }
        
        @Override
        public int compareTo(Map o) {
            return this.w - o.w;
        }
    }
    
    static int INF = 987654321;
    static int digit[][][];
    static int N;
    static int M;
    
    public int solution(int[][] board) {
        N = board.length;
        M = board[0].length;
        digit = new int[4][N][M];
        for(int d=0;d<4;d++)
            for(int i=0;i<N;i++)
                Arrays.fill(digit[d][i], INF);
        
        Queue<Map> q = new PriorityQueue<Map>();
        digit[0][0][0] = 0;
        digit[2][0][0] = 0;
        q.add(new Map(0,0,0,0));
        q.add(new Map(0,0,2,0));
        
        int answer = INF;
        while(!q.isEmpty()) {
            Map cur = q.poll();
            
            if(cur.y == N-1 && cur.x == M-1){
                answer =Math.min(answer,cur.w);
                continue;
            }
            
            for(int d=0;d<4;d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if(ny<0||nx<0||ny>=N||nx>=M) continue;
                if(board[ny][nx] == 1) continue;
                
                int nw = getWeight(cur.d, d);
                if(digit[d][ny][nx] < cur.w + nw) continue;
                    
                digit[d][ny][nx] = cur.w + nw;
                q.add(new Map(ny,nx,d,cur.w + nw));
            }
        }
        
        return answer;
    }
    
    static int getWeight(int cd, int nd) {
        if((cd == 0 || cd == 1) && (nd == 0 || nd == 1))
            return 100;
        
        if((cd == 2 || cd == 3) && (nd == 2 || nd == 3))
            return 100;
        
        if((cd == 0 || cd == 1) && (nd == 2 || nd == 3))
            return 600;
        
        if((cd == 2 || cd == 3) && (nd == 0 || nd == 1))
            return 600;
        
        return 100;
    }
}