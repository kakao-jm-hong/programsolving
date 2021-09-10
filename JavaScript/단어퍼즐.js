const INF = 987654321;

function solution(strs, t) {
    const n = t.length;
    var digit = Array.from({length: n},() => INF);

    const q = [];
    q.push([-1,0]);
    while(!(q.length === 0)) {
        const [index, count] = q.shift();

        for(let str of strs) {
            let len = str.length;

            if(index + 1 + len > n ) {
                continue;
            }

            const subStr = t.substring(index + 1 ,index + 1 + len);
            if(str === subStr && digit[index + len] > count + 1) {
                
                digit[index + len] = count + 1;

                q.push([index + len, count + 1]);
            }
        }
    }

    if(digit[t.length - 1] === INF)
        return -1;

    return digit[t.length - 1];
}

var ret =solution(["app","ap","p","l","e","ple","pp"],"apple");
console.log(ret);