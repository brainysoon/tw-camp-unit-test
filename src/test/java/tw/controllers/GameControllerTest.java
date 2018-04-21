package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.model.GuessResult;
import tw.views.GameView;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {

    private GameController gameController;
    private Game mockGame;
    private GameView mockGameView;
    private InputCommand mockInputCommand;

    @Before
    public void setUp() {

        mockGame = mock(Game.class);
        mockGameView = mock(GameView.class);
        mockInputCommand = mock(InputCommand.class);

        gameController = new GameController(mockGame, mockGameView);
    }

    @Test
    public void should_trigger_show_begin_game_when_call_begin_game() throws Exception {
        gameController.beginGame();

        verify(mockGameView).showBegin();
    }

    @Test
    public void should_trigger_show_game_status_when_call_play_and_status_is_not_continue() throws Exception {
        when(mockGame.checkCoutinue()).thenReturn(false);
        when(mockGame.checkStatus()).thenReturn("status");

        gameController.play(mockInputCommand);
    }

    @Test
    public void should_show_and_play_again_when_check_is_continue() throws Exception {
        Answer answer = Answer.createAnswer("input");
        GuessResult guessResult = new GuessResult("result", answer);
        List<GuessResult> guessResults = Arrays.asList(guessResult, guessResult);

        when(mockGame.checkCoutinue()).thenReturn(true).thenReturn(false);
        when(mockInputCommand.input()).thenReturn(answer);
        when(mockGame.guess(answer)).thenReturn(guessResult);
        when(mockGame.guessHistory()).thenReturn(guessResults);
        GameController spyGameController = spy(gameController);

        spyGameController.play(mockInputCommand);

        verify(mockGame).guess(answer);
        verify(mockGameView).showGuessResult(guessResult);
        verify(mockGameView).showGuessHistory(guessResults);
        verify(spyGameController, times(2)).play(mockInputCommand);
    }
}