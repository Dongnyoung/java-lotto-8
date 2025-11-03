package domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final int ticketCount;
    private final List<List<Integer>> userNumbers=new ArrayList<>();
    public LottoTickets(int ticketCount){
        this.ticketCount = ticketCount;
    }
    public void printBanner(){
        System.out.println(ticketCount+"개를 구매했습니다.");
        for(int i=0;i<ticketCount;i++){
            System.out.println(userNumbers.get(i));
        }
    }

    public void issueTicket() {
        for (int i = 0; i < ticketCount; i++) {
            List<Integer> numbers = new ArrayList<>(
                    Randoms.pickUniqueNumbersInRange(1, 45, 6) // 불변 → 가변으로 복사
            );
            Collections.sort(numbers);
            userNumbers.add(numbers);
        }
    }

    public List<List<Integer>> getUserNumbers(){
        return userNumbers;
    }
}
