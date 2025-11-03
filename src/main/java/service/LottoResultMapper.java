package service;

import domain.WinningMap;
import lotto.LottoResult;

import java.util.ArrayList;
import java.util.List;

public class LottoResultMapper {
    public List<LottoResult> getLottoResults(WinningMap winningMap) {
        List<LottoResult> results = new ArrayList<>();
        List<WinningMap.PrizeRank> prizeRanks = winningMap.getPrizeRanks();
        for (WinningMap.PrizeRank rank : prizeRanks) {
            results.add(new LottoResult(
                    rank.getDescription(),
                    rank.getPrize(),
                    winningMap.getCount(rank)
            ));
        }
        return results;
    }
}
