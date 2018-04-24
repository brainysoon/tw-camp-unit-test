package tw.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.FieldSetter;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;
import tw.core.model.Record;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {

    private Answer mockAnswer;
    private Game game;

    @Before
    public void setUp() throws Exception {

        AnswerGenerator mockAnswerGenerator = mock(AnswerGenerator.class);
        mockAnswer = mock(Answer.class);
        when(mockAnswerGenerator.generate()).thenReturn(mockAnswer);

        game = new Game(mockAnswerGenerator);
    }

    @Test
    public void should_return_an_expected_guess_result() {
        Record record = new Record();
        record.increaseCurrentNum();
        record.increaseIncludeOnlyNum();
        Answer answer = Answer.createAnswer("1 2 3 4");

        when(mockAnswer.check(answer)).thenReturn(record);

        GuessResult expectedGuessResult = new GuessResult("1A1B", answer);
        assertEquals(expectedGuessResult.getResult(), game.guess(answer).getResult());
        assertEquals(expectedGuessResult.getInputAnswer(), game.guess(answer).getInputAnswer());
    }

    @Test
    public void should_add_the_result_to_the_result_history_list() {
        Record record = new Record();
        record.increaseCurrentNum();
        record.increaseIncludeOnlyNum();
        Answer answer = Answer.createAnswer("1 2 3 4");

        when(mockAnswer.check(answer)).thenReturn(record);

        GuessResult guessResult = game.guess(answer);
        List<GuessResult> guessResults = game.guessHistory();

        assertTrue(guessResults.contains(guessResult));
    }

    @Test
    public void should_return_success_when_the_game_is_done() {
        Record record = new Record();
        record.increaseCurrentNum();
        record.increaseCurrentNum();
        record.increaseCurrentNum();
        record.increaseCurrentNum();
        Answer answer = Answer.createAnswer("1 2 3 4");

        when(mockAnswer.check(answer)).thenReturn(record);

        game.guess(answer);

        assertEquals("success", game.checkStatus());
    }

    @Test
    public void should_return_fail_when_is_out_of_times() throws Exception {
        Record record = new Record();
        Answer answer = Answer.createAnswer("1 2 3 4");
        List<GuessResult> mockGuessResult = mock(List.class);

        when(mockAnswer.check(answer)).thenReturn(record);
        when(mockGuessResult.size()).thenReturn(10);

        FieldSetter.setField(game, game.getClass().getDeclaredField("guessResults"), mockGuessResult);

        assertEquals("fail", game.checkStatus());
    }

    @Test
    public void should_return_continue_when_still_has_chance_to_try() {
        Record record = new Record();
        Answer answer = Answer.createAnswer("1 2 3 4");

        when(mockAnswer.check(answer)).thenReturn(record);

        game.guess(answer);

        assertEquals("continue", game.checkStatus());
    }

    @Test
    public void should_return_true_if_there_still_has_chance_to_try() {

        Record record = new Record();
        Answer answer = Answer.createAnswer("1 2 3 4");

        when(mockAnswer.check(answer)).thenReturn(record);

        game.guess(answer);

        assertTrue(game.checkCoutinue());
    }

    @Test
    public void should_return_false_if_there_not_has_any_chance() throws Exception {
        Record record = new Record();
        Answer answer = Answer.createAnswer("1 2 3 4");
        List<GuessResult> mockGuessResult = mock(List.class);

        when(mockAnswer.check(answer)).thenReturn(record);
        when(mockGuessResult.size()).thenReturn(10);

        FieldSetter.setField(game, game.getClass().getDeclaredField("guessResults"), mockGuessResult);

        assertFalse(game.checkCoutinue());
    }
}
