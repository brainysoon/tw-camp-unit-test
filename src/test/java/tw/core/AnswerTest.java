package tw.core;

import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.model.Record;

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

    @Test(expected = OutOfRangeAnswerException.class)
    public void should_throw_an_OutOfRangeAnswerException_when_answer_contains_invalid_number() throws Exception {
        Answer answer = Answer.createAnswer("11 2 33 44");

        answer.validate();
    }

    @Test
    public void should_not_throw_any_exceptions_when_all_number_is_valid() throws Exception {
        Answer answer = Answer.createAnswer("1 2 3 4");

        answer.validate();
    }

    @Test
    public void should_only_increase_current_num_when_number_value_and_position_is_correct() {
        Answer answer = Answer.createAnswer("1 2 3 4");

        Record record = answer.check(Answer.createAnswer("1 5 7 8"));

        assertEquals(1, record.getValue()[0]);
        assertEquals(0, record.getValue()[1]);
    }

    @Test
    public void should_only_increase_include_only_num_when_only_value_match() {
        Answer answer = Answer.createAnswer("1 2 3 4");

        Record record = answer.check(Answer.createAnswer("5 1 7 8"));

        assertEquals(0, record.getValue()[0]);
        assertEquals(1, record.getValue()[1]);
    }

    @Test
    public void should_return_the_index_of_the_number_when_the_number_exist() {
        Answer answer = Answer.createAnswer("1 2 3 4");

        assertEquals(2, answer.getIndexOfNum("3"));
    }

    @Test
    public void should_return_nagitive_one_when_the_number_is_not_exist() {
        Answer answer = Answer.createAnswer("1 2 3 4");

        assertEquals(-1, answer.getIndexOfNum("11"));
    }

    @Test
    public void should_return_the_numberList_item_separated_with_space() {
        Answer answer = Answer.createAnswer("1 2 3 4");

        assertEquals("1 2 3 4", answer.toString());
    }
}