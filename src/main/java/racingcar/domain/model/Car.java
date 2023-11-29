package racingcar.domain.model;

import java.util.Objects;
import java.util.function.BooleanSupplier;

public class Car {
    private final CarName name;
    private final CarLocation currentLocation;
    private final BooleanSupplier movingStrategy;

    public Car(String name, BooleanSupplier movingStrategy) {
        this.name = new CarName(name);
        this.currentLocation = CarLocation.create();
        this.movingStrategy = movingStrategy;
    }

    public void tryMoveForward() {
        if (movingStrategy.getAsBoolean()) {
            currentLocation.moveForward();
        }
    }

    public String toStringAsFitFormat() {
        return String.format("%5s : %s", name.toString(), currentLocation.getLocationByHyphen());
    }

    public Car compareTo(Car target) {
        Objects.requireNonNull(target);

        CarLocation winningLocation = this.currentLocation.compareTo(target.currentLocation);
        if (winningLocation.equals(this.currentLocation)) {
            return this;
        }
        return target;
    }

    public boolean hasSameLocation(Car target) {
        if (null == target) {
            return false;
        }

        if (this == target) {
            return true;
        }
        return this.currentLocation.equals(target.currentLocation);
    }

    public String getName() {
        return this.name.toString();
    }
}
