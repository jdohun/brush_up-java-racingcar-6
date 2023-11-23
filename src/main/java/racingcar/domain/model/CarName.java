package racingcar.domain.model;

public class CarName {
    private static final String ERROR_LENGTH = "이름은 5자 이하만 가능합니다.";
    private static final int LIMIT_LENGTH = 5;
    private String name;

    private CarName(String name) {
        this.name = name;
    }

    public static CarName from(String name) {
        validateLength(name);
        return new CarName(name);
    }

    private static void validateLength(String name) {
        if (LIMIT_LENGTH < name.length()) {
            throw new IllegalArgumentException(ERROR_LENGTH);
        }
    }
}
