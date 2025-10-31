package domain;

public class Cost {
    private final int amount;
    private final int divider = 1000;
    public Cost(String costStr){
        amount = parseCost(costStr);
        validatePositive(amount);
        validateDivideUnit(amount);
    }
    private int parseCost(String costStr) {
        if (!costStr.matches("\\d+")) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
        }
        return Integer.parseInt(costStr);
    }
    private void validateDivideUnit(int amount) {
        if (amount % divider != 0) {
            throw new IllegalArgumentException("[ERROR] 1000원 단위여야 합니다.");
        }
    }
    private void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1원 이상이어야 합니다.");
        }
    }
    public int getAmount() {
        return amount;
    }
    public int getTicket(){
        return amount/divider;
    }
}
