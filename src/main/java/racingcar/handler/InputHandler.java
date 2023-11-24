package racingcar.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {
    private static final Pattern FORMAT_INVALID_INPUT_EMPTY = Pattern.compile("^\\s*$");
    private static final Pattern FORMAT_INVALID_INPUT_NAMES = Pattern.compile("^\\s*,|,\\s*,|,\\s*$");
    private static final Pattern FORMAT_VALID_INPUT_NUMBER_OF_ATTEMPTS = Pattern.compile("^\\d+$");
    private static final String DELIMITER_INPUT_NAMES = ",";

    public static InputHandler getInstance() {
        return Holder.INPUT_HANDLER;
    }

    public List<String> inputNamesToSourceForLineUp(String inputNames) {
        validateInputNames(inputNames);

        List<String> lineUpSource = new ArrayList<>();
        for (String inputName : inputNames.split(DELIMITER_INPUT_NAMES)) {
            lineUpSource.add(inputName.trim());
        }
        return lineUpSource;
    }

    private void validateInputNames(String inputNames) {
        validateNotNull(inputNames);
        validateNotEmpty(inputNames);
        validateInputNamesFormat(inputNames);
    }

    private void validateInputNamesFormat(String inputNames) {
        Matcher matcher = FORMAT_INVALID_INPUT_NAMES.matcher(inputNames);
        if (matcher.find()) {
            throw new IllegalArgumentException("자동차 이름 입력 형식에 맞지 않습니다.");
        }
    }

    public int parseInputNumberOfAttempts(String inputNumberOfAttempts) {
        validateNotNull(inputNumberOfAttempts);
        validateNotEmpty(inputNumberOfAttempts.trim());
        validateInputNumberOfAttempts(inputNumberOfAttempts);
        return Integer.parseInt(inputNumberOfAttempts);
    }

    private void validateInputNumberOfAttempts(String inputNumberOfAttempts) {
        validateInputNumberOfAttemptsFormat(inputNumberOfAttempts);
    }

    private void validateInputNumberOfAttemptsFormat(String inputNumberOfAttempts) {
        Matcher matcher = FORMAT_VALID_INPUT_NUMBER_OF_ATTEMPTS.matcher(inputNumberOfAttempts);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("시도 횟수 입력 형식에 맞지 않습니다.");
        }
    }

    private void validateNotNull(String input) {
        if (null == input) {
            throw new IllegalArgumentException("null 을 입력할 수 없습니다.");
        }
    }

    private void validateNotEmpty(String input) {
        Matcher matcher = FORMAT_INVALID_INPUT_EMPTY.matcher(input);
        if (matcher.matches()) {
            throw new IllegalArgumentException("빈 값을 입력할 수 없습니다.");
        }
    }

    private static final class Holder {
        private static final InputHandler INPUT_HANDLER = new InputHandler();
    }

}
