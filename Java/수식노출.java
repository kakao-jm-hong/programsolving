import java.util.*;

class Solution {
    static String[][] combinations = {{"+","-","*"},{"+","*","-"},{"-","+","*"},{"-","*","+"},{"*","+","-"},{"*","-","+"}};
    
    public long solution(String expression) {
        String split[] = expression.split("(?<=[*/()+-])|(?=[*/+()-])");
        
        long answer = 0;
        
        for(String combination[] : combinations) {
            ArrayList<String> list = new ArrayList<>(Arrays.asList(split));
            
            for(String combi : combination) {
                ArrayList<String> next = new ArrayList<>();
                next.add(list.get(0));
                
                for(int i=1;i<list.size();i+=2) {
                    if(combi.equals(list.get(i))){
                        String prev = next.get(next.size() - 1);
                        String cur = calc(prev,list.get(i+1),list.get(i));
                        next.set(next.size() - 1, cur);
                    }else {
                        next.add(list.get(i));
                        next.add(list.get(i+1));
                    }
                }
                
                list = next;
            }
            
            long value = Long.parseLong(list.get(0));
            
            answer = Math.max(Math.abs(value),answer);
        }
        
        return answer;
    }
    
    public static String calc(String num1, String num2, String ch) {
        switch(ch) {
            case "+":
                return (Long.parseLong(num1) + Long.parseLong(num2))+"";
            case "-":
                return (Long.parseLong(num1) - Long.parseLong(num2))+"";
            case "*":
                return (Long.parseLong(num1) * Long.parseLong(num2))+"";
            default:
                return "";
        }
    }
}