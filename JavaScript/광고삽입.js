const getTime = (time) => {
    const [h,m,s] = time.split(":");
    return Number(h) * 60 * 60 + Number(m) * 60 + Number(s); 
};

const getFormat = (n) => {
    const format = n + "";
    const len = format.length;
    
    let ret = "";
    for(let i=0;i<2-len;i++){
        ret+="0";
    }
    
    return ret + format;
};

const getAnswerTime = (time) => {
    const hh = Math.floor(time / (60 * 60));
    time %= (60 * 60);
    const mm = Math.floor(time / (60));
    time %= (60);
    const ss = time;
    
    return getFormat(hh) +":" + getFormat(mm) +":"+ getFormat(ss);
};

const ad = new Array(360001).fill(0);

function solution(play_time, adv_time, logs) {
    const play = getTime(play_time);
    const adv = getTime(adv_time);
    
    for(let str of logs) {
        const [a , b] = str.split("-");
        const start = getTime(a);
        const end = getTime(b);
        
        ad[start] ++;
        ad[end] --;
    }
    
    for(let i=1; i<=play; i++) {
        ad[i] = ad[i] + ad[i-1];
    }
    
    for(let i=1; i<=play; i++) {
        ad[i] = ad[i] + ad[i-1];
    }
    
    let answer = 0;
    
    let start = 0;
    let end = adv;
    let answer_max = end > play ? ad[play] - ad[0] : ad[end] - ad[0];
    
    while(end <= play) {
        const sum = ad[end] - ad[start];
        if(sum > answer_max) {
            answer_max = sum;
            answer = start + 1;
        }
        
        start++;
        end++;
    }
    
    return getAnswerTime(answer);
}