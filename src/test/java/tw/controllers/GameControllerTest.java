package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import tw.commands.InputCommand;
import tw.core.Game;
import tw.views.GameView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        verify(mockGameView).showGameStatus("status");
    }
}