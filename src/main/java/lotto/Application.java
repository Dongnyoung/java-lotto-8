package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import domain.Cost;

import java.util.*;

class LottoController{
    private static final String ERROR_MESSAGE = "[ERROR]";
    private Lotto lotto;
    private Cost cost;
    public void run(){
        System.out.println("구입금액을 입력해 주세요.");
        while (true) {
            try {
                String costStr = Console.readLine();
                cost = new Cost(costStr);
                break; // 정상 입력이면 while 탈출
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        //발행한 로또 및 번호를 출력
        int lottoCountNumber = cost.getTicket();
        System.out.println(lottoCountNumber+"개를 구매했습니다.");

        List<List<Integer>> userNumbers = new ArrayList<>();
        for(int i=0;i<lottoCountNumber;i++){
            userNumbers.add(Randoms.pickUniqueNumbersInRange(1,45,6));
            System.out.println(userNumbers.get(i));
        }

        List<Integer> winningNumbersList = new ArrayList<>();
        while(true){
            try{
                winningNumbersList.clear();
                System.out.println("당첨 번호를 입력해 주세요.");
                //당첨번호 입력받기
                String winningNumbers = Console.readLine();


                //당첨번호 처리
                String[] winningNumbersStrList = winningNumbers.split(",");
                for(int i=0;i<winningNumbersStrList.length;i++){
                    winningNumbersList.add(Integer.parseInt(winningNumbersStrList[i]));
                }

                //로또번호
                lotto = new Lotto(winningNumbersList);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

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
                if (winningNumbersList.contains(bonusNumber)) {
                    throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
                }

                break;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }



        //당첨통계
        Map<String,Integer> winningMap = new HashMap<>();

        //당첨맵 초기화
        winningMap.put("3개 일치 (5,000원) - ",0);
        winningMap.put("4개 일치 (50,000원) - ",0);
        winningMap.put("5개 일치 (1,500,000원) - ",0);
        winningMap.put("5개 일치, 보너스 볼 일치 (30,000,000원) - ",0);
        winningMap.put("6개 일치 (2,000,000,000원) - ",0);

        for (List<Integer> ticket : userNumbers) {
            int matchCount = 0;

            // 당첨 번호 리스트를 그대로 사용
            for (int n : ticket) {
                if (winningNumbersList.contains(n)) {
                    matchCount++;
                }
            }

            // 보너스 번호 일치 여부 확인
            boolean bonusHit = ticket.contains(bonusNumber);

            // 결과 분기
            if (matchCount == 6) {
                winningMap.put("6개 일치 (2,000,000,000원) - ",
                        winningMap.get("6개 일치 (2,000,000,000원) - ") + 1);
            } else if (matchCount == 5 && bonusHit) {
                winningMap.put("5개 일치, 보너스 볼 일치 (30,000,000원) - ",
                        winningMap.get("5개 일치, 보너스 볼 일치 (30,000,000원) - ") + 1);
            } else if (matchCount == 5) {
                winningMap.put("5개 일치 (1,500,000원) - ",
                        winningMap.get("5개 일치 (1,500,000원) - ") + 1);
            } else if (matchCount == 4) {
                winningMap.put("4개 일치 (50,000원) - ",
                        winningMap.get("4개 일치 (50,000원) - ") + 1);
            } else if (matchCount == 3) {
                winningMap.put("3개 일치 (5,000원) - ",
                        winningMap.get("3개 일치 (5,000원) - ") + 1);
            }
        }

        List<LottoResult> results = List.of(
                new LottoResult("3개 일치 (5,000원) - ", 5000, winningMap.get("3개 일치 (5,000원) - ")),
                new LottoResult("4개 일치 (50,000원) - ", 50000, winningMap.get("4개 일치 (50,000원) - ")),
                new LottoResult("5개 일치 (1,500,000원) - ", 1500000, winningMap.get("5개 일치 (1,500,000원) - ")),
                new LottoResult("5개 일치, 보너스 볼 일치 (30,000,000원) - ", 30000000, winningMap.get("5개 일치, 보너스 볼 일치 (30,000,000원) - ")),
                new LottoResult("6개 일치 (2,000,000,000원) - ", 2000000000, winningMap.get("6개 일치 (2,000,000,000원) - "))
        );

        System.out.println("당첨통계");
        System.out.println("---");
        //수익률
        int totalPrize = 0;
        for (LottoResult r : results) {
            System.out.println(r.description + r.count + "개");
            totalPrize +=r.prize *r.count;
        }
        float rate = (float) totalPrize / cost.getAmount() * 100;
        System.out.printf("총 수익률은 %,.1f%%입니다.%n", rate);
    }
    private int parseCost(String costStr) {
        if (!costStr.matches("\\d+")) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
        }
        return Integer.parseInt(costStr);
    }

}

public class Application {
    private static Lotto lotto;

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
