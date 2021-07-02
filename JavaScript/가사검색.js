const ALPHABET = 26;

class TrieNode {
    constructor(){
        this.children = Array(ALPHABET).fill(null);
        this.isEndOfWord = false;
        this.count = 0;
    }
}

function insert(key, root) {
    let level;
    let length = key.length;
    let index;

    root.count++;
    let pCrawl = root;
    
    for(level=0; level<length; ++level) {
        index = key[level].charCodeAt(0) - 'a'.charCodeAt(0);
        if(pCrawl.children[index] === null)
            pCrawl.children[index] = new TrieNode();
        pCrawl.children[index].count++;
        pCrawl = pCrawl.children[index];
    }
    pCrawl.isEndOfWord = true;
}

function find(key, root) {
    let level;
    let length = key.length;
    let index;
    let pCrawl = root;
    for(level=0;level<length;++level) {
        if(key[level] === '?')
            return pCrawl.count;
        index = key[level].charCodeAt(0) - 'a'.charCodeAt(0);
        if(pCrawl.children[index] === null)
            return 0;

        pCrawl = pCrawl.children[index];
    }

    return pCrawl.count;
}

const frontRoot = Array.from({length:10001},() => new TrieNode());
const rearRoot = Array.from({length:10001},() => new TrieNode());

function solution(words, queries) {
    
    // 정방향, 반대방향 Trie 길이별 생성
    for(let word of words) {
        let len = word.length;
        insert(word,frontRoot[len]);
        insert(word.split("").reverse().join(""),rearRoot[len]);
    }
    var answer = [];
    // 정답 찾기 query
    for(let query of queries) {
        // 정방향
        let len = query.length;
        if(query[0] !== '?') {
            answer.push(find(query,frontRoot[len]));
        }
        // 역방향
        else {
            answer.push(find(query.split("").reverse().join(""),rearRoot[len]))
        }
    }
    return answer;
}

console.log(solution(["frodo", "front", "frost", "frozen", "frame", "kakao"],["fro??", "????o", "fr???", "fro???", "pro?"]));
