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

    public CarLocation compareTo(CarLocation targetLocation) {
        if (this.location >= targetLocation.location) {
            return this;
        }
        return targetLocation;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (null == obj || !(obj instanceof CarLocation target)) {
            return false;
        }

        return this.location == target.location;
    }

    @Override
    public int hashCode() {
        return location;
    }
}
