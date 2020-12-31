function solution(a, b) {
    let Month = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    let ans = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
    let stand = 5;

    for (let i = 1; i < a; i++) {
        stand += Month[i-1];
    }
    stand += b-1;
    return ans[stand%7];
}

console.log(solution(5, 24));