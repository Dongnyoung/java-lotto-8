package io;

import camp.nextstep.edu.missionutils.Console;
import domain.Cost;

public class CostInput {

    public Cost inputCost() {
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                String input = Console.readLine();
                return new Cost(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // 재입력 유도
            }
        }
    }
}
