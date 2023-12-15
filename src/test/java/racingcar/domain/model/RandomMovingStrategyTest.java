package racingcar.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.IntSupplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RandomMovingStrategyTest {

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

    @Nested
    @DisplayName("생성 숫자에 대한 통과 여부 테스트")
    class MovingStrategyTest {


        @DisplayName("범위를 벗어나면 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(ints = {-1, 10})
        void validateTest(int outOfRangeValue) {
            // Arrange
            RandomMovingStrategy movingStrategy = RandomMovingStrategy.from(new FixNumberGenerator(outOfRangeValue));

            // Act
            // Assert
            assertThatThrownBy(movingStrategy::getAsBoolean)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("범위를 벗어나는 값을 생성합니다. 생성 값: " + outOfRangeValue);
        }

        @DisplayName("생성 숫자에 대한 결과 테스트")
        @ParameterizedTest
        @CsvSource(value = {"0,false", "3,false", "4,true", "9,true"})
        void getAsBooleanTest(int generated, boolean expected) {
            // Arrange
            RandomMovingStrategy movingStrategy = RandomMovingStrategy.from(new FixNumberGenerator(generated));

            // Act
            boolean result = movingStrategy.getAsBoolean();

            // Assert
            assertThat(result).isEqualTo(expected);
        }
    }
}