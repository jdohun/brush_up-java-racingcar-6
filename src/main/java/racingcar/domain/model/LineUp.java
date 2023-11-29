package racingcar.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LineUp {
    private final List<Car> cars;

    public LineUp(List<Car> cars) {
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
            cars.add(new Car(inputName, RandomMovingStrategy.create()));
        }
        return new LineUp(cars);
    }

    public void tryCarsMoveForward() {
        for (Car car : cars) {
            car.tryMoveForward();
        }
    }

    public List<String> toStringListAsFormattedCarsCondition() {
        List<String> carsLocation = new ArrayList<>();
        for (Car car : cars) {
            carsLocation.add(car.toStringAsFormattedCondition());
        }
        return carsLocation;
    }

    public List<Car> findWinners() {
        Car winner = findWinner();
        List<Car> winners = new ArrayList<>();

        for (Car car : cars) {
            if (car.hasSameLocation(winner)) {
                winners.add(car);
            }
        }

        return winners;
    }

    private Car findWinner() {
        Car winner = cars.get(0);
        for (int i = 1; i < cars.size(); i++) {
            winner = winner.compareTo(cars.get(i));
        }
        return winner;
    }
}
