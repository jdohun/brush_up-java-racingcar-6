package racingcar.controller;

import racingcar.domain.model.LineUp;
import racingcar.handler.InputHandler;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {
    private static final InputView INPUT_VIEW = InputView.getInstance();
    private static final InputHandler INPUT_HANDLER = InputHandler.getInstance();
    private static final OutputView OUTPUT_VIEW = OutputView.getInstance();

    public void run() {
        LineUp lineUp = initializeLineUp();
        int numberOfAttempts = initializeNumberOfAttempts();
        for (int i = 0; i < numberOfAttempts; i++) {
            lineUp.tryCarsMoveForward();
            OUTPUT_VIEW.broadcasting(lineUp.toStringListAsFormattedCarsCondition());
        }
        OUTPUT_VIEW.prizeCeremony(lineUp.findWinners());
    }

    private LineUp initializeLineUp() {
        return LineUp.from(INPUT_HANDLER.inputNamesToSourceForLineUp(INPUT_VIEW.inputNames()));
    }

    private int initializeNumberOfAttempts(){
        return INPUT_HANDLER.parseInputNumberOfAttempts(INPUT_VIEW.inputNumberOfAttempts());
    }
}
