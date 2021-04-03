package kakaocumerce;

public class a {
    public int solution(int[] gift_cards, int[] wants) {
        int [] buffer = new int[100001];
        for(int card : gift_cards) {
        	buffer[card]++;
        }
    	int answer = 0;
    	for(int want : wants) {
    		if(buffer[want] ==0) {
    			answer ++;
    		}else {
    			buffer[want]--;
    		}
    	}
        return answer;
    }
}
