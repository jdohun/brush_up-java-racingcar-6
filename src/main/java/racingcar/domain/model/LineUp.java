package racingcar.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LineUp {
    private final List<Car> cars;

    private LineUp(List<Car> cars) {
        validate(cars);
        this.cars = cars;
    }

    private void validate(List<Car> cars) {
        Objects.requireNonNull(cars);
        validateEmpty(cars);
    }

    private void validateEmpty(List<Car> cars) {
        if(cars.isEmpty()){
            throw new IllegalArgumentException("전달 받은 리스트가 비어있습니다.");
        }
    }

    public static LineUp from(List<String> lineUpSource) {
        List<Car> cars = new ArrayList<>();
        for (String inputName : lineUpSource) {
            cars.add(new Car(inputName));
        }
        return new LineUp(cars);
    }

    public void tryCarsMoveForward() {
        for (Car car : cars) {
            car.tryMoveForward();
        }
    }

    public List<String> asFitFormatCarsLocation() {
        List<String> carsLocation = new ArrayList<>();
        for (Car car : cars) {
            carsLocation.add(car.toStringAsFitFormat());
        }
        return carsLocation;
    }
}
