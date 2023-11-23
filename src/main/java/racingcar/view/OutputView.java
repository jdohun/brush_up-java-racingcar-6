package racingcar.view;

import java.util.List;

public final class OutputView {

    public static OutputView getInstance() {
        return Holder.OUTPUT_VIEW;
    }

    public void printResult(List<String> carsLocation) {
        for (String carLocation : carsLocation) {
            System.out.println(carLocation);
        }
    }

    private static final class Holder {
        private static final OutputView OUTPUT_VIEW = new OutputView();
    }
}
