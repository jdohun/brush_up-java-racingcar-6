package racingcar.domain.model;

import racingcar.util.StringValidater;

class CarName {
    private static final int LIMIT_LENGTH = 5;
    private String name;

    public CarName(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        StringValidater.validateNotNull(name);
        StringValidater.validateNotEmpty(name);
        StringValidater.validateHasNotSurroundingWhiteSpace(name);
        validateLength(name);
    }

    private void validateLength(String name) {
        if (LIMIT_LENGTH < name.length()) {
            throw new IllegalArgumentException("이름은 5자 이하만 가능합니다.");
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
