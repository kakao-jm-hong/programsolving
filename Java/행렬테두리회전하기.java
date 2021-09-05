import java.util.*;

class Solution {
    static int map[][];
    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];
        int count = 1;
        
        for(int i=0;i<rows;i++) {
            for(int j=0;j<columns;j++) {
                map[i][j] = count++;      
            }
        }
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        for(int[] query : queries) {
            list.add(Process(query[0],query[1],query[2],query[3]));
        }
        
        int[] answer = getAnswer(list);
        return answer;
    }
    
    public int Process(int y1,int x1, int y2, int x2) {
        int n = y2 - y1 + 1;
        int m = x2 - x1 + 1;
        int copy[][] = new int [n][m];
        for(int i= y1 - 1; i< y2; i++) {
            for(int j= x1 -1 ; j< x2; j++) {
                copy[i-y1+1][j-x1+1] = map[i][j];
            }
        }
        
        int minValue = Integer.MAX_VALUE;
        copy = Rotate(copy);
        
        for(int i= y1 - 1; i< y2; i++) {
            for(int j= x1 -1 ; j< x2; j++) {
               map[i][j] = copy[i-y1+1][j-x1+1];
            }
        }
        
        for(int j=0;j<m-1;j++)
            minValue = Math.min(minValue,copy[0][j]);
        for(int j=m-1;j>0;j--)
            minValue = Math.min(minValue,copy[n-1][j]);
        for(int i=0;i<n-1;i++)
            minValue = Math.min(minValue,copy[i][m-1]);
        for(int i=n-1;i>0;i--)
            minValue = Math.min(minValue,copy[i][0]);
        
        return minValue;
    }
    
    public int[][] Rotate(int[][] origin) {
        int n = origin.length;
        int m = origin[0].length;
        int arr[][] = new int[n][m];
        
        for(int i=0;i<n;i++) {
            arr[i] = origin[i].clone();
        }
        
        for(int j=0;j<m-1;j++)
            arr[0][j+1] = origin[0][j];
        for(int j=m-1;j>0;j--)
            arr[n-1][j-1] = origin[n-1][j];
        for(int i=0;i<n-1;i++)
            arr[i+1][m-1] = origin[i][m-1];
        for(int i=n-1;i>0;i--)
            arr[i-1][0] = origin[i][0];
        
        return arr;
    }
    
    public int[] getAnswer(ArrayList<Integer> list) {
        int[] answer = new int[list.size()];
        for(int i =0;i<list.size();i++)
            answer[i] = list.get(i);
        return answer;
    }
}