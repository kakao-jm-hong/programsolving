
var list;
var stack = [];
var answer;

const Delete = (k) => {
    stack.push([k,list[k][0],list[k][1]]);
    let pre = list[k][0];
    let next = list[k][1];
    
    answer[k] = false;
    
    if(next === -1){
        list[pre][1] = next;
        k = pre;
    }else{
        list[next][0] = pre;
        list[pre][1] = next;
        k = next;
    }
};

const Down = (k,m) => {
    for(let i=0;i<m;i++)
        k = list[k][1];
    return k;
        
};

const Up = (k,m) => {
    for(let i=0;i<m;i++)
        k = list[k][0];
    return k;
};

const GetBack = () => {
    const s = stack.pop();
    const k = s[0];
    const pre = s[1];
    const next = s[2];
    
    list[pre][1] = k;
    list[next][0] = k;
    
    answer[k] = true;
};

function solution(n, k, cmd) {
    // LinkedList
    list = Array.from({length: n}, (value,index) => ([index-1,index+1]));
    answer = Array(n).fill(true);
    list[n-1][1] = -1;                      
    for(let c of cmd) {
        let order = c.split(' ');
        
        switch(order[0]){
            //down 
            case 'D':
                k = Down(k,order[1]);
            break;
            //delete
            case 'C':
                Delete(k);
            break;
            // up
            case 'U':
                k = Up(k,order[1]);
            break;
            // privious
            case 'Z':
                GetBack();
            break;
        }
    }
    
    var ret = [];
    for(let i=0;i<n;i++){
        if(answer[i]){
            ret.psuh('O');
        }else{
            ret.push('X');
        }
    }
    
    return ret.join();
}