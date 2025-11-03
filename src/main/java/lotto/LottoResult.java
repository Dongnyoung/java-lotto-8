package lotto;

public class LottoResult {
    private String description;
    private int prize;
    private int count;

    public LottoResult(String description, int prize, int count) {
        this.description = description;
        this.prize = prize;
        this.count = count;
    }
    public String getDescription() {
        return description;
    }
    public int getPrize() {
        return prize;
    }
    public int getCount() {
        return count;
    }
}
