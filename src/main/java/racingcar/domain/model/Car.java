package racingcar.domain.model;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private final int START_OF_RANDOM_RANGE = 0;
    private final int END_OF_RANDOM_RANGE = 9;
    private final int PASSING_CRITERIA = 4;

    private final CarName name;
    private final CarLocation currentLocation;

    public Car(String name) {
        this.name = new CarName(name);
        this.currentLocation = CarLocation.create();
    }

    public void tryMoveForward() {
        if (PASSING_CRITERIA <= generateRandomNumber()) {
        }
    }

    private int generateRandomNumber() {
        return Randoms.pickNumberInRange(START_OF_RANDOM_RANGE, END_OF_RANDOM_RANGE);
    }

    public String toStringAsFitFormat() {
        return String.format("%5s : %s", name.toString(), currentLocation.getLocationByHyphen());
    }
}
