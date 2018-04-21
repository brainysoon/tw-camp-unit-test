package tw.core.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RandomIntGeneratorTest {

    private RandomIntGenerator randomIntGenerator;

    @Before
    public void setUp() throws Exception {

        randomIntGenerator = new RandomIntGenerator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_IllegalArgumentException_when_digitmax_less_thant_numbersOfNeed() {
        randomIntGenerator.generateNums(4, 9);
    }

    @Test
    public void should_generate_an_array_of_number() {
        String randomNumber = randomIntGenerator.generateNums(9, 4);

        Assert.assertEquals(4, randomNumber.split(" ").length);
    }
}
