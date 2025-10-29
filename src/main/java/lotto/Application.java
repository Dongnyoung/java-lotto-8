package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class Application {
    private static Lotto lotto;

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        //구입 금액 입력받기
        System.out.println("구입금액을 입력해 주세요.");
        String costStr = Console.readLine();
        char[] costCheckCharList = costStr.toCharArray();
        for(char c: costCheckCharList) {
            if(!Character.isDigit(c)) {
                throw new IllegalArgumentException("[ERROR] 숫자가 아닌 입력발생");
            }
        }

        final int divider = 1000;

        //구입 금액 정수형 변환
        int cost = Integer.parseInt(costStr);

        //1000원으로 나누어 떨어지는지 유효성검사
        if(cost%divider!=0){
            throw new IllegalArgumentException("[ERROR] 1000원으로 나누어 떨어지지 않음");
        }

        //발행한 로또 및 번호를 출력
        int lottoCountNumber = cost/divider;
        System.out.println(lottoCountNumber+"개를 구매했습니다.");

        List<List<Integer>> userNumbers = new ArrayList<>();
        for(int i=0;i<lottoCountNumber;i++){
            userNumbers.add(Randoms.pickUniqueNumbersInRange(1,45,6));
            System.out.println(userNumbers.get(i));
        }

        List<Integer> winningNumbersList = new ArrayList<>();
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

        System.out.println("보너스 번호를 입력해 주세요.");
        //보너스 번호 입력받기
        String bonusNumberStr = Console.readLine();
        int bonusNumber = Integer.parseInt(bonusNumberStr);

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
        float rate = (float) totalPrize / cost * 100;
        System.out.printf("총 수익률은 %,.1f%%입니다.%n", rate);
    }
}
