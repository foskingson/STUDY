package foskingson.tybeconverter.formatter;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class MyNumberFormatterTest {

    MyNumberFormatter formatter = new MyNumberFormatter();

    @Test
    void testParse() throws ParseException {
        Number res = formatter.parse("1,000", Locale.KOREA);
        assertThat(res).isEqualTo(1000L);
    }   

    @Test
    void testPrint() {
        String result = formatter.print(1000, Locale.KOREA);
        assertThat(result).isEqualTo("1,000");
    }
}
