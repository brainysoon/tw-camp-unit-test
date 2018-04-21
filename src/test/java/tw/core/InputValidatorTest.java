package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.validator.InputValidator;

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
}
