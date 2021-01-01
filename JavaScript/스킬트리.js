function solution(skill, skill_trees) {
    var answer = 0;
    var size = skill.length;

    for (let i = 0; i < skill_trees.length; i++) {

        let str = String();

        for (let j = 0; j < skill_trees[i].length; j++) {
            if (skill.includes(skill_trees[i].charAt(j))) {
                str += skill_trees[i].charAt(j);
            }
        }

        if (str == skill.substring(0,str.length)) {
            answer++;
        }
    }
    return answer;
}

solution("CBD", ["AB",]);