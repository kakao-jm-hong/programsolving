import java.util.*;

class Solution {
    static int dy[] = {1,-1,0,0};
    static int dx[] = {0,0,1,-1};
    
    static class Pair {
        int y;
        int x;
        
        Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    public int solution(int[][] maps) {
        Queue<Pair> q = new LinkedList<Pair>();
        maps[0][0] = 0;
        q.add(new Pair(0,0));
        int N = maps.length;
        int M = maps[0].length;
        int time = 0;
        while(!q.isEmpty()) {
            ++time;
            int size = q.size();
            
            for(int s=0;s<size;s++) {
                Pair cur = q.poll();
                if(cur.y == N-1 && cur.x == M-1)
                    return time;
                    
                for(int d=0;d<4;d++) {
                    int ny = dy[d] + cur.y;
                    int nx = dx[d] + cur.x;
                    if(ny<0||ny>=N||nx<0||nx>=M)
                        continue;
                    if(maps[ny][nx] == 0)
                        continue;
                    q.add(new Pair(ny,nx));
                    maps[ny][nx] = 0;
                }
            }
        }
        
        return -1;
    }
}