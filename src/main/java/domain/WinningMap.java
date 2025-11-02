package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningMap {
    private final Map<PrizeRank, Integer> winningMap = new HashMap<>();

    public WinningMap() {
        initialize();
    }

    private void initialize() {
        for (PrizeRank rank : PrizeRank.values()) {
            winningMap.put(rank, 0);
        }
    }

    public void evaluate(List<Integer> winningLottoNumbersList, List<List<Integer>> userNumbers, int bonusNumber) {
        for (List<Integer> ticket : userNumbers) {
            int matchCount = getMatchCount(winningLottoNumbersList, ticket);
            boolean bonusHit = ticket.contains(bonusNumber);
            
            PrizeRank rank = PrizeRank.findRank(matchCount, bonusHit);
            if (rank != null) {
                winningMap.put(rank, winningMap.get(rank) + 1);
            }
        }
    }

    private static int getMatchCount(List<Integer> winningLottoNumbersList, List<Integer> ticket) {
        int matchCount = 0;
        for (int number : ticket) {
            if (winningLottoNumbersList.contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    public Map<PrizeRank, Integer> getWinningMap() {
        return new HashMap<>(winningMap);
    }

    public int getTotalPrize() {
        int totalPrize = 0;
        for (Map.Entry<PrizeRank, Integer> entry : winningMap.entrySet()) {
            totalPrize += entry.getKey().getPrize() * entry.getValue();
        }
        return totalPrize;
    }

    public int getCount(PrizeRank rank) {
        return winningMap.getOrDefault(rank, 0);
    }

    public List<PrizeRank> getPrizeRanks() {
        return List.of(
                PrizeRank.THREE_MATCH,
                PrizeRank.FOUR_MATCH,
                PrizeRank.FIVE_MATCH,
                PrizeRank.FIVE_MATCH_WITH_BONUS,
                PrizeRank.SIX_MATCH
        );
    }

    public enum PrizeRank {
        THREE_MATCH(3, false, 5000, "3개 일치 (5,000원) - "),
        FOUR_MATCH(4, false, 50000, "4개 일치 (50,000원) - "),
        FIVE_MATCH(5, false, 1500000, "5개 일치 (1,500,000원) - "),
        FIVE_MATCH_WITH_BONUS(5, true, 30000000, "5개 일치, 보너스 볼 일치 (30,000,000원) - "),
        SIX_MATCH(6, false, 2000000000, "6개 일치 (2,000,000,000원) - ");

        private final int matchCount;
        private final boolean requiresBonus;
        private final int prize;
        private final String description;

        PrizeRank(int matchCount, boolean requiresBonus, int prize, String description) {
            this.matchCount = matchCount;
            this.requiresBonus = requiresBonus;
            this.prize = prize;
            this.description = description;
        }

        public static PrizeRank findRank(int matchCount, boolean bonusHit) {
            if (matchCount == 6) {
                return SIX_MATCH;
            }
            if (matchCount == 5) {
                return bonusHit ? FIVE_MATCH_WITH_BONUS : FIVE_MATCH;
            }
            if (matchCount == 4) {
                return FOUR_MATCH;
            }
            if (matchCount == 3) {
                return THREE_MATCH;
            }
            return null;
        }

        public int getPrize() {
            return prize;
        }

        public String getDescription() {
            return description;
        }
    }
}
