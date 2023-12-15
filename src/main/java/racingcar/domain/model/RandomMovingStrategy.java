package racingcar.domain.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;

public class RandomMovingStrategy implements BooleanSupplier {
    private static final int START_OF_RANDOM_RANGE = 0;
    private static final int END_OF_RANDOM_RANGE = 9;
    private static final int PASSING_CRITERIA = 4;
    private static final IntSupplier DEFAULT_RANDOM_NUMBER_GENERATOR
            = () -> Randoms.pickNumberInRange(START_OF_RANDOM_RANGE, END_OF_RANDOM_RANGE);

    private final IntSupplier randomNumberGenerator;

    private RandomMovingStrategy(IntSupplier randomNumberGenerator) {
        this.randomNumberGenerator = Objects.requireNonNull(randomNumberGenerator);
    }

    static RandomMovingStrategy create() {
        return new RandomMovingStrategy(DEFAULT_RANDOM_NUMBER_GENERATOR);
    }

    static RandomMovingStrategy from(IntSupplier randomNumberGenerator) {
        return new RandomMovingStrategy(randomNumberGenerator);
    }

    @Override
    public boolean getAsBoolean() {
        int randomNumber = randomNumberGenerator.getAsInt();
        validate(randomNumber);

        return PASSING_CRITERIA <= randomNumber;
    }

    private void validate(int generatedNumber) {
        validateNumberInRange(generatedNumber);
    }

    private void validateNumberInRange(int generatedNumber) {
        if (START_OF_RANDOM_RANGE > generatedNumber || END_OF_RANDOM_RANGE < generatedNumber) {
            throw new IllegalArgumentException("범위를 벗어나는 값을 생성합니다. 생성 값: " + generatedNumber);
        }
    }
}
