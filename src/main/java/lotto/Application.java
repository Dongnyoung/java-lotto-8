package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import domain.Cost;
import domain.LottoTickets;
import domain.WinningMap;
import io.CostInput;
import io.LottoInput;

import java.util.*;

class LottoController{
    private static final String ERROR_MESSAGE = "[ERROR]";
    private final CostInput costInput;
    public LottoController() {
        this.costInput = new CostInput();
    }
    public void run(){
        Cost cost = costInput.inputCost();

        //발행한 로또 및 번호를 출력
        int ticketCount = cost.getTicket();
        LottoTickets lottoTickets = new LottoTickets(ticketCount);
        lottoTickets.issueTicket();
        lottoTickets.printBanner();
        List<List<Integer>> userNumbers = lottoTickets.getUserNumbers();

        //당첨번호 입력받기
        LottoInput lottoInput = new LottoInput();
        Lotto lotto = lottoInput.inputLottoNumber();
        List<Integer> winningLottoNumbersList = lotto.getNumbers();

        int bonusNumber = 0;
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                String bonusNumberStr = Console.readLine();
                bonusNumber = Integer.parseInt(bonusNumberStr);

                // 범위 체크
                if (bonusNumber < 1 || bonusNumber > 45) {
                    throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 사이여야 합니다.");
                }

                // 당첨 번호 중복 체크
                if (winningLottoNumbersList.contains(bonusNumber)) {
                    throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
                }

                break;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        //당첨 확인
        WinningMap winningMap = new WinningMap();
        winningMap.evaluate(winningLottoNumbersList, userNumbers, bonusNumber);

        //당첨 통계
        List<LottoResult> results = new ArrayList<>();
        List<WinningMap.PrizeRank> prizeRanks = winningMap.getPrizeRanks();
        for (WinningMap.PrizeRank rank : prizeRanks) {
            results.add(new LottoResult(
                    rank.getDescription(),
                    rank.getPrize(),
                    winningMap.getCount(rank)
            ));
        }

        System.out.println("당첨통계");
        System.out.println("---");
        int totalPrize = 0;
        for (LottoResult r : results) {
            System.out.println(r.description + r.count + "개");
            totalPrize += r.prize * r.count;
        }
        float rate = (float) totalPrize / cost.getAmount() * 100;
        System.out.printf("총 수익률은 %,.1f%%입니다.%n", rate);
    }
}

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        try{
            new LottoController().run();
        }
        finally{
            Console.close();//자원 정리
        }

    }
}
