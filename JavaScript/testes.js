/* 
 * `codeOwnersMap`과 `directory`를 입력받아
 * `directory`의 코드 주인 목록을 반환하는 함수를 작성하세요.
 */
function solution(codeOwnersMap, directory) {
    console.log(codeOwnersMap);
    let split = directory.split('/');
    
    if(split.length === 1) {
        return codeOwnersMap[split[0]];
    }else {
        const nextCode = codeOwnersMap[split[0]];
        split.shift();
        const nextDirectory = split.join('/');
        return solution(nextCode,nextDirectory);
    }
    
}

    
/* 코드 주인 정보 예시 */
const codeOwnersMap = {
    "scripts": ["배수진"],
    "services": {
      "business-ledger": ["고찬균", "배수진"],
      "toss-card": ["채주민", "유재섭"],
      "payments": ["유재섭"],
    }
};

  const directory = 'services/business-ledger';

  console.log(solution(codeOwnersMap,directory));

console.log('scripts'.split('/').length);