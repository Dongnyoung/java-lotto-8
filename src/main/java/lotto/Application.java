package lotto;

import camp.nextstep.edu.missionutils.Console;
import domain.Cost;
import domain.LottoTickets;
import domain.WinningMap;
import io.BonusInput;
import io.CostInput;
import io.LottoInput;
import io.ResultOutput;
import service.LottoResultMapper;

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

        //보너스 숫자 입력 , 입력값 넘겨주기
        BonusInput bonus = new BonusInput();
        BonusNumber bonusNum = bonus.bonusInput(winningLottoNumbersList);

        //보너스 숫자
        int bonusNumber = bonusNum.getBonusNumber();

        //당첨 확인
        WinningMap winningMap = new WinningMap();
        winningMap.evaluate(winningLottoNumbersList, userNumbers, bonusNumber);

        //당첨 통계
        LottoResultMapper lottoResultMapper = new LottoResultMapper();
        List<LottoResult> results = lottoResultMapper.getLottoResults(winningMap);

        //통계 출력
        ResultOutput resultOutput = new ResultOutput();
        resultOutput.printResult(results, cost);
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
