package programmers.kakao.후보키;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {
    static String[][] g_relation;
    static HashSet<String> set;

    public void dfs(int idx, int cnt, int max, boolean[] selected) {
        if (cnt == max) {
            String cols = "";
            for (int i = 0; i < selected.length; i++) {
                if (selected[i]) {
                    cols += i;
                }
            }
            if (findIsPossible(cols, selected)) {
                set.add(cols);
            }

            return;
        }

        if (idx >= selected.length) return;

        selected[idx] = true;
        dfs(idx + 1, cnt + 1, max, selected);

        selected[idx] = false;
        dfs(idx + 1, cnt, max, selected);
    }

    private boolean findIsPossible(String cols, boolean[] selected) {

        for (String s : set) {
            boolean flag = true;
            for (int i = 0; i < s.length(); i++) {
                if (!cols.contains(s.charAt(i)+"")) {
                    flag = false;
                }
            }
            if (flag) {
                return false;
            }
        }

        HashSet<String> values = new HashSet<>();
        int[] col_name = new int[cols.length()];
        int idx = 0;
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                col_name[idx++] = i;
            }
        }

        String value = "";
        for (int i = 0; i < g_relation.length; i++) {
            value = "";
            for (int j = 0; j < col_name.length; j++) {
                value += g_relation[i][col_name[j]];
            }
            if (values.contains(value)) {
                return false;
            } else {
                values.add(value);
            }
        }
        return true;
    }

    public int solution(String[][] relation) {
        g_relation = relation;
        set = new HashSet<>();

        for (int i = 1; i <= relation[0].length; i++) {
            boolean[] selected = new boolean[relation[0].length];
            dfs(0, 0, i, selected);
        }
        return set.size();
    }
}
