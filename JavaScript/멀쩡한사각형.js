function solution(w, h) {
    return w * h - (w + h - GCD(w, h));
}

function GCD(a, b) {
    let tmp, n;

    if (a < b) {
        tmp = a;
        a = b;
        b = tmp;
    }

    while (b != 0) {
        n = a % b;
        a = b;
        b = n;
    }
    return a;
}

solution(8, 12);