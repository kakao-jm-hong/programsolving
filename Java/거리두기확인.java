import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int len = places.length;
        int[] answer = new int[len];
        
        for(int i=0;i<len;i++) {
            answer[i] = Solve(places[i]);
        }
        
        return answer;
    }
    
    public class Pair {
        int y;
        int x;
        
        Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    public int Solve(String[] place) {
        char[][] map = new char[5][5];
        
        for(int i=0;i<5;i++)  map[i] = place[i].toCharArray();
        List<Pair> list = new ArrayList<>();
        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++){
                if(map[i][j] == 'P') {
                    list.add(new Pair(i,j));
                }
            }
        }
        
        for(Pair pair : list) {
            if(!Check(pair.y,pair.x, map))
                return 0;
        }
        return 1;
    }
    
    static int dy[] = {1,-1,0,0};
    static int dx[] = {0,0,1,-1};
    
    public boolean Check(int y, int x, char map[][]) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visit = new boolean[5][5];
        
        q.add(new Pair(y,x));
        visit[y][x] = true;
        
        for(int t=0;t<2;t++) {
            int size = q.size();
            for(int s=0;s<size;s++) {
                Pair cur = q.poll();
                for(int d=0;d<4;d++) {
                    int ny = dy[d] + cur.y;
                    int nx = dx[d] + cur.x;
                    if(ny<0||nx<0||ny>=5||nx>=5)
                        continue;
                    if(visit[ny][nx])
                        continue;
                    if(map[ny][nx] == 'X')
                        continue;
                    if(map[ny][nx] == 'P')
                        return false;

                    visit[ny][nx] = true;
                    q.add(new Pair(ny,nx));
                }
            }
        }
        
        return true;
    }
}