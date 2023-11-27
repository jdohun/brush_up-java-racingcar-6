package racingcar.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringValidaterTest {

    @DisplayName("null 을 입력 받으면 예외처리가 발생한다.")
    @Test
    void validateNotNull() {
        assertThatThrownBy(() -> StringValidater.validateNotNull(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null 을 입력할 수 없습니다.");

    }

    @DisplayName("빈 문자열을 입력 받으면 예외처리가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void validateNotEmpty(String empty) {
        assertThatThrownBy(() -> StringValidater.validateNotEmpty(empty))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 문자열을 입력할 수 없습니다.");
    }

    @DisplayName("공백으로 시작하거나 공백으로 끝나는 문자열을 입력 받으면 예외처리가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {" test", "test ", " test "})
    void validateHasNotWhiteSpace(String startOrFinishByWhiteSpace) {
        assertThatThrownBy(() -> StringValidater.validateHasNotWhiteSpace(startOrFinishByWhiteSpace))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("문자열의 시작과 끝에 공백을 입력할 수 없습니다.");
    }
}