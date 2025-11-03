package lotto;

import java.util.List;

public class BonusNumber {
    private final int value;

    public BonusNumber(int value, List<Integer> winningNumbers) {
        validateRange(value);
        validateDuplication(value, winningNumbers);
        this.value = value;
    }

    public int getBonusNumber() {
        return value;
    }

    private void validateRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 사이여야 합니다.");
        }
    }

    private void validateDuplication(int number, List<Integer> winningNumbers) {
        if (winningNumbers.contains(number)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
