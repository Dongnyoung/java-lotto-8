package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        checkDuplication(numbers);
        checkRange(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    // TODO: 추가 기능 구현
    private void checkDuplication(List<Integer> numbers) {
        //당첨번호 중복 확인
        Set<Integer> numberSet = new HashSet<>();
        for(int number : numbers){
            if(!numberSet.add(number)){ //add가 false면 이미 존재
                throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있음");
            }
        }
    }
    private void checkRange(List<Integer> numbers) {
        //당첨번호 숫자범위 체크
        for(int number : numbers){
            if(number>45 || number<1){ //1~45 사이 숫자인지 체크
                throw new IllegalArgumentException("[ERROR] 로또 번호가 숫자범위(1~45)를 벗어남");
            }
        }
    }
    public List<Integer> getNumbers() {
        return numbers;
    }
}
