package io;

import camp.nextstep.edu.missionutils.Console;
import lotto.BonusNumber;

import java.util.List;

public class BonusInput {
    public BonusNumber bonusInput(List<Integer> winningNumbers){
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                String bonusNumberStr = Console.readLine();
                int bonusNumber = parseBonusNumber(bonusNumberStr);
                return new BonusNumber(bonusNumber,winningNumbers);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int parseBonusNumber(String bonusNumberStr) {
        int bonusNumber = Integer.parseInt(bonusNumberStr);
        return bonusNumber;
    }

}
