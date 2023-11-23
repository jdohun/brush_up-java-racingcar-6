package racingcar.domain.model;

class CarLocation {
    private final int INITIAL_LOCATION = 0;

    private int location;

    private CarLocation() {
        this.location = INITIAL_LOCATION;
    }

    static CarLocation create() {
        return new CarLocation();
    }

    void moveForward() {
        ++this.location;
    }

    String getLocationByHyphen() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < location; i++) {
            sb.append("-");
        }
        return sb.toString();
    }
}
