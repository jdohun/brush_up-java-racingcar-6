package racingcar.domain.model;

import java.util.Objects;

public class CarName {
    private static final String ERROR_LENGTH = "이름은 5자 이하만 가능합니다.";
    private static final int LIMIT_LENGTH = 5;
    private String name;

    public CarName(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        Objects.requireNonNull(name);
        validateEmpty(name);
        validateLength(name);
    }

    private void validateEmpty(String name){
        if (name.isEmpty()) {
            throw new IllegalArgumentException("빈 문자열은 이름이 될 수 없습니다.");
        }
    }

    private void validateLength(String name) {
        if (LIMIT_LENGTH < name.length()) {
            throw new IllegalArgumentException(ERROR_LENGTH);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
