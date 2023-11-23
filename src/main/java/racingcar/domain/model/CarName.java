package racingcar.domain.model;

public class CarName {
    private static final String ERROR_LENGTH = "이름은 5자 이하만 가능합니다.";
    private static final int LIMIT_LENGTH = 5;
    private String name;

    public CarName(String name) {
        validateLength(name);
        this.name = name;
    }

    private void validateLength(String name) {
        if (LIMIT_LENGTH < name.length()) {
            throw new IllegalArgumentException(ERROR_LENGTH);
        }
    }
}
