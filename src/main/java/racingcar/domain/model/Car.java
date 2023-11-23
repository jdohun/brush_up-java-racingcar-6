package racingcar.domain.model;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private final int START_OF_RANDOM_RANGE = 0;
    private final int END_OF_RANDOM_RANGE = 9;
    private final int PASSING_CRITERIA = 4;

    private CarName name;
    private CarLocation currentLocation;

    public Car(String name) {
        this.name = CarName.from(name);
        this.currentLocation = CarLocation.create();
    }

    public void tryMoveForward() {
        if (PASSING_CRITERIA <= getRandomNumber()) {
            currentLocation.moveForward();
        }
    }

    private int getRandomNumber() {
        return Randoms.pickNumberInRange(START_OF_RANDOM_RANGE, END_OF_RANDOM_RANGE);
    }

    public String toStringAsFitFormat() {
        return String.format("%s : %s", name, currentLocation.getLocationByHyphen());
    }
}
