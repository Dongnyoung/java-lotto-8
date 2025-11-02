package io;

import camp.nextstep.edu.missionutils.Console;
import lotto.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoInput {
    private List<Integer> winningNumbersList = new ArrayList<>();
    public Lotto inputLottoNumber(){
        while(true){
            try{
                initialize();
                System.out.println("당첨 번호를 입력해 주세요.");
                //당첨번호 입력받기
                String winningNumbers = Console.readLine();
                winningNumbersParse(winningNumbers);
                return new Lotto(winningNumbersList);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private void initialize() {
        winningNumbersList.clear();
    }

    private void winningNumbersParse(String winningNumbers) {
        //당첨번호 처리
        String[] winningNumbersStrList = winningNumbers.split(",");
        for(int i=0;i<winningNumbersStrList.length;i++){
            winningNumbersList.add(Integer.parseInt(winningNumbersStrList[i]));
        }
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbersList;
    }
}
