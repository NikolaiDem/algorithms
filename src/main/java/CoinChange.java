class CoinChange {

    public int calcCoinChange(int[] coins, int amount) {
        int countOfAmounts = amount + 1;
        int[] minCountByAmountAsIndex = new int[countOfAmounts];
        initMinCounts(countOfAmounts, minCountByAmountAsIndex);

        for (int iterAmount = 1; iterAmount <= amount; iterAmount++) {
            int minCount = minCountByAmountAsIndex[iterAmount];
            if (minCount == -1) {
                updateArrayWithCalculatedMinCount(minCountByAmountAsIndex, coins, iterAmount);
            }
        }

        return minCountByAmountAsIndex[amount];
    }

    private void initMinCounts(int countOfAmounts, int[] minCountByIndexAsSum) {
        minCountByIndexAsSum[0] = 0;
        for (int i = 1; i < countOfAmounts; i++) {
            minCountByIndexAsSum[i] = -1;
        }
    }

    private void updateArrayWithCalculatedMinCount(int[] minCountByAmountAsIndex, int[] coins, int amount) {
        int minCount = -1;
        for (int coin : coins) {
            int prevAmount = amount - coin;
            if (prevAmount < 0 || minCountByAmountAsIndex[prevAmount] == -1) {
                continue;
            }
            minCount = getMinPositiveFrom(minCount, minCountByAmountAsIndex[prevAmount] + 1);
        }
        minCountByAmountAsIndex[amount] = minCount;
    }

    private int getMinPositiveFrom(int v1, int v2) {
        if (v1 < 0 && v2 < 0) {
            throw new IllegalArgumentException("Both arguments are negative");
        }
        return v1 <= v2 && v1 >= 0 ? v1 : v2;
    }
}