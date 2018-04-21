package tw.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    @Test
    public void should_create_an_answer_of_expected() {

        assertEquals("1 2 3 4", Answer.createAnswer("1 2 3 4").toString());
        assertEquals("11 2 33 4", Answer.createAnswer("11 2 33 4").toString());
    }
}