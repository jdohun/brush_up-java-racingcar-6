package racingcar.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {
    private static final Pattern FORMAT_INVALID_INPUT_NAMES = Pattern.compile("^\\s*,|,\\s*,|,\\s*$");
    private static final Pattern FORMAT_VALID_INPUT_NUMBER_OF_ATTEMPTS = Pattern.compile("^\\d$");
    private static final String ERROR_INVALID_INPUT_NAMES = "자동차 이름 입력 형식에 맞지 않습니다.";
    private static final String ERROR_INVALID_INPUT_NUMBER_OF_ATTEMPTS = "시도 횟수 입력 형식에 맞지 않습니다.";
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
        Matcher matcher = FORMAT_INVALID_INPUT_NAMES.matcher(inputNames);
        if (matcher.find()) {
            throw new IllegalArgumentException(ERROR_INVALID_INPUT_NAMES);
        }
    }

    public int parseInputNumberOfAttempts(String inputNumberOfAttempts) {
        validateInputNumberOfAttempts(inputNumberOfAttempts);
        return Integer.parseInt(inputNumberOfAttempts);
    }

    private void validateInputNumberOfAttempts(String inputNumberOfAttempts) {
        Matcher matcher = FORMAT_VALID_INPUT_NUMBER_OF_ATTEMPTS.matcher(inputNumberOfAttempts);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ERROR_INVALID_INPUT_NUMBER_OF_ATTEMPTS);
        }
    }

    private static final class Holder {
        private static final InputHandler INPUT_HANDLER = new InputHandler();
    }

}
