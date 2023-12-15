package racingcar.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CarLocationTest {

    @DisplayName("생성 시 초깃값은 0 이다.")
    @Test
    void createThenInitial() {
        // Arrange
        CarLocation sample = CarLocation.create();

        // Act

        // Assert
        assertThat(sample.getLocationByHyphen()).isEqualTo("");
    }

    @DisplayName("전진한 거리를 Hyphen(-) 으로 반환한다.")
    @ParameterizedTest(name = "{0}번 전진하면 {1}을 반환")
    @CsvSource(value = {"0,''", "1,-", "2,--", "3,---"})
    void getLocationByHyphen(int moveTimes, String expected) {
        // Arrange
        CarLocation sample = CarLocation.create();

        // Act
        for (int i = 0; i < moveTimes; i++) {
            sample.moveForward();
        }

        // Assert
        assertThat(sample.getLocationByHyphen()).isEqualTo(expected);
    }

    @DisplayName("같은 거리를 이동한 값은 같은 객체다.")
    @ParameterizedTest
    @CsvSource(value = {"0,0", "1,1", "2,2"})
    void testEquals(int sample1Move, int sample2Move) {
        // Arrange
        CarLocation sample1 = CarLocation.create();
        CarLocation sample2 = CarLocation.create();

        // Act
        for (int i = 0; i < sample1Move; i++) {
            sample1.moveForward();
        }

        for (int i = 0; i < sample2Move; i++) {
            sample2.moveForward();
        }

        // Assert
        assertThat(sample1.equals(sample1))
                .isTrue();

        assertThat(sample1.equals(sample2))
                .isTrue();

        assertThat(sample1.equals(sample2))
                .isTrue();

        assertThat(sample2.equals(sample1))
                .isTrue();
    }

    @DisplayName("의미하는 값이 같은 객체는 같은 해시값을 가진다.")
    @ParameterizedTest
    @CsvSource(value = {"0,0", "1,1", "2,2"})
    void testHashCode(int sample1Move, int sample2Move) {
        // Arrange
        CarLocation sample1 = CarLocation.create();
        CarLocation sample2 = CarLocation.create();

        // Act
        for (int i = 0; i < sample1Move; i++) {
            sample1.moveForward();
        }

        for (int i = 0; i < sample2Move; i++) {
            sample2.moveForward();
        }

        // Assert
        assertThat(sample1)
                .hasSameHashCodeAs(sample2);
    }

    @DisplayName("이동한 거리를 비교하여 더 많이 이동한 값을 반환한다.")
    @Test
    void compareTo() {
        int sample1Move = 1;
        int sample2Move = 2;

        // Arrange
        CarLocation sample1 = CarLocation.create();
        CarLocation sample2 = CarLocation.create();

        // Act
        for (int i = 0; i < sample1Move; i++) {
            sample1.moveForward();
        }

        for (int i = 0; i < sample2Move; i++) {
            sample2.moveForward();
        }

        // Assert
        assertThat(sample1.compareTo(sample2))
                .isEqualTo(sample2);
    }
}