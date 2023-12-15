package racingcar.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {

    public static final Pattern FORMAT_INVALID_STRING_EMPTY = Pattern.compile("^\\s*$");
    public static final Pattern FORMAT_INVALID_STRING_HAS_SURROUNDING_WHITE_SPACE = Pattern.compile("^\\S(.*\\S)?$");

    private StringValidator() {
    }

    public static void validateNotNull(String input) {
        if (null == input) {
            throw new IllegalArgumentException("null 을 입력할 수 없습니다.");
        }
    }

    public static void validateNotEmpty(String input) {
        Matcher matcher = FORMAT_INVALID_STRING_EMPTY.matcher(input);
        if (matcher.matches()) {
            throw new IllegalArgumentException("빈 문자열을 입력할 수 없습니다.");
        }
    }

    public static void validateHasNotSurroundingWhiteSpace(String input) {
        Matcher matcher = FORMAT_INVALID_STRING_HAS_SURROUNDING_WHITE_SPACE.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("문자열의 시작과 끝에 공백을 입력할 수 없습니다.");
        }
    }
}
