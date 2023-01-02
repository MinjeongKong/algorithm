package programmers.level2.숫자_카드_나누기;

class Solution {
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];

        gcdA = getGcd(arrayA, gcdA);
        gcdB = getGcd(arrayB, gcdB);

        boolean flagA = true;
        boolean flagB = true;
        for (int x : arrayB) {
            if (x % gcdA == 0) {
                flagA = false;
                break;
            }
        }
        for (int x : arrayA) {
            if (x % gcdB == 0) {
                flagB = false;
                break;
            }
        }
        if (flagA && !flagB) {
            return gcdA;
        }
        if (!flagA && flagB) {
            return gcdB;
        }
        if (flagA && flagB) {
            return Math.max(gcdA, gcdB);
        }
        return 0;
    }

    private int getGcd(int[] array, int preGcd) {
        for (int i = 0; i < array.length - 1; i++) {
            int tmp = gcd(array[i], array[i + 1]);
            preGcd = gcd(preGcd, tmp);
        }
        return preGcd;
    }
}
