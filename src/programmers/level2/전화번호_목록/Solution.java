package programmers.level2.전화번호_목록;

import java.util.HashMap;

class Solution {
    public boolean solution(String[] phoneBook) {
        HashMap<String, Integer> hp = new HashMap<>();
        for (String s : phoneBook) {
            hp.put(s, hp.getOrDefault(s, 0) + 1);
        }
        for (int i = 0; i < phoneBook.length; i++) {
            for (int j = 0; j < phoneBook[i].length(); j++) {
                if (hp.containsKey(phoneBook[i].substring(0, j))) {
                    return false;
                }
            }
        }

        return true;
    }
}
