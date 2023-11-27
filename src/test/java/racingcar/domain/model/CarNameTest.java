package racingcar.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.util.StringValidater;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarNameTest {

    @DisplayName("저장된 이름을 문자열로 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"ABCDE", "가나다라마", "일이삼사오"})
    void toStringTest(String input) {
        CarName name = new CarName(input);
        assertThat(name.toString())
                .isEqualTo(input);
    }

    @Nested
    class createTest {

        @DisplayName("null 을 입력 받으면 예외처리가 발생한다.")
        @Test
        void createByNull() {
            assertThatThrownBy(() -> new CarName(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("null 을 입력할 수 없습니다.");
        }

        @DisplayName("빈 문자열을 입력 받으면 예외처리가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"", " ", "   "})
        void createByEmpty(String empty) {
            assertThatThrownBy(() -> new CarName(empty))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("빈 문자열을 입력할 수 없습니다.");
        }

        @DisplayName("공백으로 시작하거나 공백으로 끝나는 문자열을 입력 받으면 예외처리가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {" test", "  test", " test ", "  test  ", "test ", "test  " })
        void validateHasNotSurroundingWhiteSpace(String surroundingWhiteSpace) {
            assertThatThrownBy(() -> new CarName(surroundingWhiteSpace))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("문자열의 시작과 끝에 공백을 입력할 수 없습니다.");
        }

        @DisplayName("길이가 5 를 초과하는 문자열을 입력 받으면 예외처리가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"123456", "ㅁㄴㅇㅁㄴㅇ", "abcdef"})
        void createByOverLength(String lengthOver) {
            assertThatThrownBy(() -> new CarName(lengthOver))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 5자 이하만 가능합니다.");
        }
    }
}