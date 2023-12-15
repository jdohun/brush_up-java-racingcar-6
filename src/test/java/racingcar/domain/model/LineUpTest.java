package racingcar.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LineUpTest {
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

    @DisplayName("LineUp 생성 테스트")
    @Nested
    class createTest {

        @DisplayName("전달 받은 문자열 리스트가 비어있으면 예외가 발생한다.")
        @Test
        void createByFrom() {
            // Arrange
            List<String> emptyNames = new ArrayList<>();

            // Act
            // Assert
            assertThatThrownBy(() -> LineUp.from(emptyNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("전달 받은 리스트가 비어있습니다.");
        }

        @DisplayName("전달 받은 Car 리스트가 비어있으면 예외가 발생한다.")
        @Test
        void createByNew() {
            // Arrange
            List<Car> emptyCars = new ArrayList<>();

            // Act
            // Assert
            assertThatThrownBy(() -> new LineUp(emptyCars))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("전달 받은 리스트가 비어있습니다.");
        }
    }

    @DisplayName("LineUp 조작 테스트")
    @Nested
    class ControlTest {

        @DisplayName("Cars 를 전진시킨 결과를 문자열 리스트로 반환한다.")
        @Test
        void asFitFormatCarsLocation() {
            // Arrange
            List<Car> cars = Arrays.asList(
                    new Car("test1", RandomMovingStrategy.from(new FixNumberGenerator(1))),
                    new Car("test2", RandomMovingStrategy.from(new FixNumberGenerator(4)))
            );

            LineUp lineUp = new LineUp(cars);

            // Act
            lineUp.tryCarsMoveForward();
            List<String> formattedCarsCondition = lineUp.toStringListAsFormattedCarsCondition();

            // Assert
            assertThat(formattedCarsCondition)
                    .isEqualTo(Arrays.asList(
                            "test1 : ",
                            "test2 : -"
                    ));

        }

        @DisplayName("모든 우승자를 반환한다.")
        @ParameterizedTest
        @CsvSource(value = {"1,1,4", "1,4,4", "4,4,1", "4,1,4"})
        void findWinners(int num1, int num2, int num3) {
            // Arrange
            Car sample1 = new Car("test1", RandomMovingStrategy.from(new FixNumberGenerator(num1)));
            Car sample2 = new Car("test2", RandomMovingStrategy.from(new FixNumberGenerator(num2)));
            Car sample3 = new Car("test3", RandomMovingStrategy.from(new FixNumberGenerator(num3)));

            List<Car> cars = Arrays.asList(
                    sample1,
                    sample2,
                    sample3
            );

            LineUp lineUp = new LineUp(cars);

            // Act
            lineUp.tryCarsMoveForward();
            List<Car> winners = lineUp.findWinners();

            // Assert
            if (num1 >= 4) {
                assertThat(winners).contains(sample1);
            }

            if (num1 < 4) {
                assertThat(winners).isNotIn(sample1);
            }

            if (num2 >= 4) {
                assertThat(winners).contains(sample2);
            }

            if (num2 < 4) {
                assertThat(winners).isNotIn(sample2);
            }

            if (num3 >= 4) {
                assertThat(winners).contains(sample3);
            }

            if (num3 < 4) {
                assertThat(winners).isNotIn(sample3);
            }
        }
    }
}