package tw.core.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tw.core.Answer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    private AnswerGenerator answerGenerator;
    private RandomIntGenerator mockRandomIntGenerator;

    @Before
    public void setUp() throws Exception {

        mockRandomIntGenerator = mock(RandomIntGenerator.class);
        answerGenerator = new AnswerGenerator(mockRandomIntGenerator);
    }

    @Test
    public void should_generate_an_answer_when_call_generate() throws Exception {
        when(mockRandomIntGenerator.generateNums(9, 4)).thenReturn("1 2 3 4");

        Answer answer = answerGenerator.generate();

        Assert.assertNotNull(answer);
        Assert.assertEquals("1 2 3 4", answer.toString());
    }
}

