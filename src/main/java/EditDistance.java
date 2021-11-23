class EditDistance {

    public int minDistance(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        int[][] cash = new int[l1][l2];
        initCash(l1, l2, cash);
        return distLev(word1, word2, word1.length()-1, word2.length()-1, cash);
    }
    
    private int distLev(String s1, String s2, int i1, int i2, int[][] cash){
        if(i1 <0 || i2 < 0){
            return Math.max(i1 + 1, i2 + 1);
        } else if(cash[i1][i2] == -1){
            int minDist = minFrom(
                    distLev(s1, s2, i1 - 1, i2, cash) + 1,
                    distLev(s1, s2, i1, i2 - 1, cash) + 1,
                    distLev(s1, s2, i1 - 1, i2 - 1, cash) + compareCharsAtIndexes(s1, s2, i1, i2));
            cash[i1][i2] = minDist;
        }
        return cash[i1][i2];
    }
    
    private int minFrom(int v1, int v2, int v3){
        return Math.min(v3, Math.min(v1, v2));
    }
    
    private int compareCharsAtIndexes(String s1, String s2, int i1, int i2){
        return s1.charAt(i1) == s2.charAt(i2) ? 0 : 1;
    }
    
    private void initCash(int l1, int l2, int[][] cash) {
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                cash[i][j] = -1;
            }
        }
    }
}