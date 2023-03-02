package programmers.level3.다단계_칫솔_판매;

import java.util.HashMap;

class Node {
    String name;
    int amount;
    Node node;

    public Node(String name) {
        this.name = name;
        this.amount = 0;
        this.node = null;
    }
}

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        HashMap<String, Node> hp = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            hp.put(enroll[i], new Node(enroll[i]));
            if (!referral[i].equals("-")) {
                hp.get(enroll[i]).node = hp.get(referral[i]);
            }
        }
        for (int i = 0; i < seller.length; i++) {
            plusAmount(amount[i] * 100, hp.get(seller[i]));
        }
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = hp.get(enroll[i]).amount;
        }
        return answer;
    }

    public void plusAmount(int amount, Node node) {
        int parentAmount = (int) (amount * 0.1);
        int childAmount = amount - parentAmount;

        if (parentAmount < 1) {
            node.amount += amount;
            return;
        }
        node.amount += childAmount;
        if (node.node == null) {
            return;
        }
        plusAmount(parentAmount, node.node);
    }
}
