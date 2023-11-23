package racingcar.domain.model;

import java.util.ArrayList;
import java.util.List;

public class LineUp {
    private final List<Car> cars;

    public LineUp(List<Car> cars) {
        this.cars = cars;
    }

    public static LineUp from(List<String> lineUpSource) {
        List<Car> cars = new ArrayList<>();
        for (String inputName : lineUpSource) {
            cars.add(new Car(inputName));
        }
        return new LineUp(cars);
    }
}
