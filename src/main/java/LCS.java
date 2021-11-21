import java.util.*;

class LCS {

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        int l1 = text1.length();
        int l2 = text2.length();
        int[][] cash = new int[l1][l2];
        initCash(l1, l2, cash);
        return lcs(text1, text2, l1 - 1, l2 - 1, cash);
    }

    private void initCash(int l1, int l2, int[][] cash) {
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                cash[i][j] = -1;
            }
        }
    }

    private int lcs(String text1, String text2, int i1, int i2, int[][] cash) {
        if (i1 < 0 || i2 < 0) {
            return 0;
        }
        boolean isValueInCash = cash[i1][i2] != -1;
        if (isValueInCash) {
            return cash[i1][i2];
        }
        if (text1.charAt(i1) == text2.charAt(i2)) {
            int longestLength = lcs(text1, text2, i1 - 1, i2 - 1, cash);
            cash[i1][i2] = longestLength + 1;
        } else {
            int longestLength = Math.max(lcs(text1, text2, i1 - 1, i2, cash), lcs(text1, text2, i1, i2 - 1, cash));
            cash[i1][i2] = longestLength;
        }
        return cash[i1][i2];
    }
}