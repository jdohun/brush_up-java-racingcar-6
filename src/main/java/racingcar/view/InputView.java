package racingcar.view;

import camp.nextstep.edu.missionutils.Console;

public final class InputView {

    public static InputView getInstance() {
        return Holder.INPUT_VIEW;
    }

    public String inputNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        return Console.readLine();
    }

    public String inputNumberOfAttempts() {
        System.out.println("시도할 회수는 몇회인가요?");
        return Console.readLine();
    }

    private static final class Holder {
        private static final InputView INPUT_VIEW = new InputView();
    }
}
