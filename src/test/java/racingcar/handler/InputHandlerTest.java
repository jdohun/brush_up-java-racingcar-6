package racingcar.handler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class InputHandlerTest {

    private final InputHandler INPUT_HANDLER = InputHandler.getInstance();

    @Nested
    class HandlingInputNameTest {
        @DisplayName("null 을 입력하면 예외 처리한다.")
        @Test
        void validateInputAsNull() {
            assertThatThrownBy(() -> INPUT_HANDLER.inputNamesToSourceForLineUp(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("null 을 입력할 수 없습니다.");
        }

        @DisplayName("빈 문자열을 입력하면 예외 처리한다.")
        @ParameterizedTest
        @ValueSource(strings = {"", " ", "   "})
        void validateInputAsEmpty(String empty) {
            assertThatThrownBy(() -> INPUT_HANDLER.inputNamesToSourceForLineUp(empty))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("빈 문자열을 입력할 수 없습니다.");
        }

        @DisplayName("쉼표로 시작하거나 연속되거나 끝나면 예외 처리한다.")
        @ParameterizedTest
        @ValueSource(strings = {", pobi", "pobi,", "pobi,,jun", "pobi,  ,jun"})
        void validateInputHasCommasIssue(String commasIssue) {
            assertThatThrownBy(() -> INPUT_HANDLER.inputNamesToSourceForLineUp(commasIssue))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("자동차 이름 입력 형식에 맞지 않습니다.");
        }

        @DisplayName("올바르게 입력하면 List<String> 를 반환한다.")
        @ParameterizedTest
        @ValueSource(strings = {"pobi,woni,jun", "pobi, woni, jun", "pobi , woni , jun"})
        void validateInputAsRightInput(String rightInput) {

            assertThat(INPUT_HANDLER.inputNamesToSourceForLineUp(rightInput))
                    .isEqualTo(Arrays.asList(
                            "pobi",
                            "woni",
                            "jun"
                    ));
        }
    }

    @Nested
    class HandlingInputNumberOfAttemptsTest {
        @DisplayName("null 을 입력하면 예외 처리한다.")
        @Test
        void validateInputAsNull() {
            assertThatThrownBy(() -> INPUT_HANDLER.parseInputNumberOfAttempts(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("null 을 입력할 수 없습니다.");
        }

        @DisplayName("빈 문자열 을 입력하면 예외 처리한다.")
        @ParameterizedTest
        @ValueSource(strings = {"", " ", "   "})
        void validateInputAsEmpty(String empty) {
            assertThatThrownBy(() -> INPUT_HANDLER.parseInputNumberOfAttempts(empty))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("빈 문자열을 입력할 수 없습니다.");
        }

        @DisplayName("숫자가 아니면 예외 처리한다.")
        @ParameterizedTest
        @ValueSource(strings = {"ㅂ", "1ㅂㅈㅇㄴㅁㅋ"})
        void validateInputAsNotNumber(String notNumber) {
            assertThatThrownBy(() -> INPUT_HANDLER.parseInputNumberOfAttempts(notNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("시도 횟수 입력 형식에 맞지 않습니다.");
        }

        @DisplayName("숫자를 입력하면 정수를 반환한다.")
        @ParameterizedTest
        @CsvSource(value = {"1, 1", "12, 12", "123, 123"})
        void validateInputAsNumber(String inputNumber, int expected) {
            assertThat(INPUT_HANDLER.parseInputNumberOfAttempts(inputNumber))
                    .isEqualTo(expected);
        }
    }
}
