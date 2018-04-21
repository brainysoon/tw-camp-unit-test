package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.validator.InputValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {

    private InputValidator inputValidator;

    @Before
    public void setUp() throws Exception {

        inputValidator = new InputValidator();
    }

    @Test
    public void should_return_true_if_num_string_is_valid() {

        assertTrue(inputValidator.validate("1 2 3 4"));
    }

    @Test
    public void should_return_false_if_num_length_is_not_4() {

        assertFalse(inputValidator.validate("1 3 4"));
    }

    @Test
    public void should_return_false_if_num_contain_value_that_greater_than_10() {

        assertFalse(inputValidator.validate("11 1 3 4"));
    }

    @Test(expected = NumberFormatException.class)
    public void should_throw_an_number_format_exception_when_contain_non_number() {

        inputValidator.validate("3 2 3 8*");
    }
}
