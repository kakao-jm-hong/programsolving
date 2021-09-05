import java.util.*;

class Solution {
    static int[] dy = {1,-1,0,0};
    static int[] dx = {0,0,1,-1};
    static int numberOfArea;
    static int maxSizeOfOneArea;
    static int M,N;
    
    public int[] solution(int m, int n, int[][] picture) {
        numberOfArea = 0;
        maxSizeOfOneArea = 0;

        int visit[][] = new int[m][n];
        
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(picture[i][j] != 0 && visit[i][j] == 0) BFS(visit,picture,m,n,i,j);
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public class Pair {
        int y;
        int x;
        
        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    public void BFS(int visit[][], int picture[][], int m, int n ,int y, int x) {
        int sizeOfOneArea = 0;
        int color = ++numberOfArea;
        int p = picture[y][x];
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(y, x));
        visit[y][x] = color;
        
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            sizeOfOneArea++;
            
            for(int d=0;d<4;d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                
                if(ny<0||nx<0||ny>=m||nx>=n)
                    continue;
                if(visit[ny][nx] != 0)
                    continue;
                if(picture[ny][nx] != p)
                    continue;
                
                q.add(new Pair(ny,nx));
                visit[ny][nx] = color;
            }
        }
        
        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, sizeOfOneArea);
        
    }
}