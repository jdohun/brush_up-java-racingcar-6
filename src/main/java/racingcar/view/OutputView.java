package racingcar.view;

import racingcar.domain.model.Car;

import java.util.List;
import java.util.StringJoiner;

public final class OutputView {

    public static OutputView getInstance() {
        return Holder.OUTPUT_VIEW;
    }

    public void broadcasting(List<String> carsLocation) {
        for (String carLocation : carsLocation) {
            System.out.println(carLocation);
        }
        System.out.println();
    }

    public void prizeCeremony(List<Car> winners) {
        System.out.printf("최종 우승자 : ");
        StringJoiner sj = new StringJoiner(", ");
        for (Car winner : winners) {
            sj.add(winner.getName());
        }
        System.out.println(sj);
    }

    private static final class Holder {
        private static final OutputView OUTPUT_VIEW = new OutputView();
    }
}
