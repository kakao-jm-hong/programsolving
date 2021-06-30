function solution(food_times, k) {
    var cnt = 0;
    var foods = food_times.map((time, index) => {
        cnt += time;
        return [time, index + 1];
    }).sort((a, b)=> {
        return a[0] - b[0];
    });

    if(cnt <= k)
        return -1;

    var len = foods.length;
    var minus = 0;
    let i =0;
    for(i=0; i<len; i++){
        let tmp = len - i;     
        let f = foods[i][0] - minus;

        if(k - tmp * f >= 0) {
            minus += f;
            k -= tmp * f;
        }else {
            let gab = Math.floor(k / tmp);
            k -= gab * tmp;
            break;
        }
    }

    const answer = foods.slice(i,len).sort((a, b) => a[1] - b[1]);

    return answer[k][1];
}

console.log(solution([3, 6, 6],1));