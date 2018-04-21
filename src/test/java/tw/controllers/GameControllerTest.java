package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import tw.core.Game;
import tw.views.GameView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {

    private GameController gameController;
    private Game mockGame;
    private GameView mockGameView;

    @Before
    public void setUp() {

        mockGame = mock(Game.class);
        mockGameView = mock(GameView.class);

        gameController = new GameController(mockGame, mockGameView);
    }

    @Test
    public void should_trigger_show_begin_game_when_call_begin_game() throws Exception {
        gameController.beginGame();

        verify(mockGameView).showBegin();
    }
}