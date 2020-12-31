function solution(a) {
    var size = a.length;
    var left = new Array(size);
    var right = new Array(size);

    left[0] = a[0];
    right[size - 1] = a[size - 1];

    for (let i = 1; i < size; i++) {
        left[i] = Math.min(left[i - 1], a[i]);
    }
    for (let i = size - 2; i >= 0; i--) {
        right[i] = Math.min(right[i + 1], a[i]);
    }
    const map = new Map();
    for (let i = 0; i < size; i++) {
        map.set(left[i]);
        map.set(right[i]);
    }
    var answer = map.size;
    return answer;
}

console.log(solution([9, -1, -5]));