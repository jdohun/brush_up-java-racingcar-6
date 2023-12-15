package racingcar.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.IntSupplier;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {

    @DisplayName("결과에 따라 전진하고 정해진 형식으로 문자열을 반환한다.")
    @ParameterizedTest(name = "{0}의 {1} 값에 대한 이동 결과는 {2}")
    @CsvSource(value = {"test1, 1, 'test1 : '", "test2, 4, 'test2 : -'"})
    void toStringAsFitFormat(String name, int generated, String expected) {
        // Arrange
        Car sample = new Car(name, RandomMovingStrategy.from(new FixNumberGenerator(generated)));

        // Act
        sample.tryMoveForward();
        String result = sample.toStringAsFormattedCondition();

        // Assert
        assertThat(result)
                .isEqualTo(expected);
    }

    @DisplayName("두 자동차의 위치를 비교하여 더 많이 전진한 자동차를 반환한다.")
    @Test
    void compareTo() {
        // Arrange
        Car sample1 = new Car("test1", RandomMovingStrategy.from(new FixNumberGenerator(1)));
        Car sample2 = new Car("test2", RandomMovingStrategy.from(new FixNumberGenerator(4)));

        // Act
        sample1.tryMoveForward();
        sample2.tryMoveForward();

        // Assert
        assertThat(sample1.compareTo(sample2))
                .isEqualTo(sample2);

        assertThat(sample2.compareTo(sample1))
                .isEqualTo(sample2);
    }

    @DisplayName("두 자동차의 이동한 거리가 같은지 비교 결과 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1, 4, false", "1, 0,true", "4,4,true"})
    void hasSameLocation(int generated1, int generated2, boolean expected) {
        // Arrange
        Car sample1 = new Car("test1", RandomMovingStrategy.from(new FixNumberGenerator(generated1)));
        Car sample2 = new Car("test2", RandomMovingStrategy.from(new FixNumberGenerator(generated2)));

        // Act
        sample1.tryMoveForward();
        sample2.tryMoveForward();

        // Assert
        assertThat(sample1.hasSameLocation(sample2))
                .isEqualTo(expected);
    }

    @DisplayName("저장된 이름을 문자열로 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"test1", "test2"})
    void getName(String inputName) {
        // Arrange
        Car sample = new Car(inputName, RandomMovingStrategy.create());

        // Act
        String name = sample.getName();

        // Assert
        assertThat(inputName)
                .isEqualTo(name);
    }

    private static class FixNumberGenerator implements IntSupplier {
        private final int fixedNumber;

        private FixNumberGenerator(int number) {
            this.fixedNumber = number;
        }

        @Override
        public int getAsInt() {
            return fixedNumber;
        }
    }
}