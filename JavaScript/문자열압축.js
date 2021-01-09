function solution(s) {
    var answer = 987654321;

    for (let len = 1; len <= s.length; len++) {

        let str = '';
        let tmp = s.substring(0, len);
        let pivot = len;
        let cnt = 1;
        while (pivot < s.length) {
            if (pivot + len > s.length) {
                if (cnt == 1) {
                    str += tmp;
                } else {
                    str += cnt + tmp;
                }
                tmp = s.substring(pivot, s.length);
                cnt = 1;
                break;
            }

            let cur = s.substring(pivot, pivot + len);
            if (tmp == cur) {
                cnt++;
            } else {
                if (cnt == 1) {
                    str += tmp;
                } else {
                    str += cnt + tmp;
                }
                tmp = cur;
                cnt = 1;
            }
            pivot += len;
        }
        if (cnt == 1) {
            str += tmp;
        } else {
            str += cnt + tmp;
        }
        answer = Math.min(answer, str.length);
    }
    return answer;
}
console.log(solution("abcabcdede"));