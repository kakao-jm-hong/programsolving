import java.util.*;

class Solution {
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        for(int i =0; i< s.length(); i++) {
            char cur = s.charAt(i);
            if(stack.size() == 0 || stack.peek() != cur)
                stack.push(cur);
            else if(stack.peek() == cur)
                stack.pop();
        }
        
        return stack.size() == 0 ? 1 : 0;
    }
}