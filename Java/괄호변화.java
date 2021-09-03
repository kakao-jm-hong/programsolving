import java.util.*;

class Solution {
    public String solution(String p) {
        int len = p.length();
        if(len == 0)
            return "";
        
        // u v 분리
        int open = 0;
        int close = 0;
        int i =0;
        for(i=0;i<len;i++) {
            if(p.charAt(i) == '(')
                open++;
            else 
                close++;
            
            if(open!= 0 && open == close)
                break;
        }
        String u = p.substring(0,i+1);
        String v = p.substring(i+1,len);
        
        if(isPossible(u)){
            return u + solution(v);
        }else {
            // return "(" + solution(v) + ")" + new StringBuffer(u.substring(1,u.length()-1)).reverse().toString();
            return "(" + solution(v) + ")" + getString(u.substring(1,u.length()-1));
        }
        
    }
    
    public boolean isPossible(String u) {
        char[] arr = u.toCharArray();
        Stack<Character> stack = new Stack<>();
        
        for(int i=0;i<arr.length;i++) {
            if(arr[i] == '(')
                stack.push('(');
            else {
                if(stack.isEmpty())
                    return false;
                stack.pop();
            }
        }
        
        if(!stack.isEmpty())
            return false;
        
        return true;
    }
    
    public String getString(String str) {
        StringBuffer sb = new StringBuffer();
        for(char c : str.toCharArray()) {
            if(c == '(')
                sb.append(')');
            else 
                sb.append('(');
        }
        return sb.toString();
    }
}