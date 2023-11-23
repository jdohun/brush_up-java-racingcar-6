package racingcar.domain.model;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private final int START_OF_RANDOM_RANGE = 0;
    private final int END_OF_RANDOM_RANGE = 9;
    private final int PASSING_CRITERIA = 4;

    private String name;
    private int currentLocation = 0;

    public Car(String name) {
        this.name = name;
    }

    public boolean tryMove() {
        if (PASSING_CRITERIA <= getRandomNumber()) {
            ++currentLocation;
            return true;
        }
        return false;
    }

    private int getRandomNumber() {
        return Randoms.pickNumberInRange(START_OF_RANDOM_RANGE, END_OF_RANDOM_RANGE);
    }

    public String toStringFitFormat() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentLocation; i++) {
            sb.append("-");
        }
        return String.format("%s : %s", name, sb);
    }
}
