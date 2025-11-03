package io;

import domain.Cost;
import lotto.LottoResult;

import java.util.List;

public class ResultOutput {

    public void printResult(List<LottoResult> results, Cost cost) {
        resultOpeningBanner();
        float rate = getRate(results, cost);
        System.out.printf("총 수익률은 %,.1f%%입니다.%n", rate);
    }

    private static float getRate(List<LottoResult> results, Cost cost) {
        int totalPrize = 0;
        for (LottoResult r : results) {
            System.out.println(r.getDescription() + r.getCount() + "개");
            totalPrize += r.getPrize() * r.getCount();
        }
        float rate = (float) totalPrize / cost.getAmount() * 100;
        return rate;
    }

    private static void resultOpeningBanner() {
        System.out.println("당첨통계");
        System.out.println("---");
    }
}
