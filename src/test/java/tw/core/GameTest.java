package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;
import tw.core.model.Record;

import static org.junit.Assert.assertEquals;
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
}
