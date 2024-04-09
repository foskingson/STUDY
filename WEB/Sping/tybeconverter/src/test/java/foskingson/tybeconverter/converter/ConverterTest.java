package foskingson.tybeconverter.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import foskingson.tybeconverter.type.IpPort;

public class ConverterTest {
    
    @Test
    void 문자를숫자로(){
        StringToIntegerConverter cv = new StringToIntegerConverter();
        Integer result = cv.convert("10");
        assertThat(result).isEqualTo(10);
    }

    @Test
    void 숫자를문자로(){
        IntegerToStringConverter cv = new IntegerToStringConverter();
        String result = cv.convert(10);
        assertThat(result).isEqualTo("10");
    }

    @Test
    void 문자를ipport로(){
        IpPortToStringConverter cv = new IpPortToStringConverter();
        String result = cv.convert(new IpPort("127.0.0.1", 8080));
        assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    void ipport를문자로(){
        StringToIpPortConverter cv = new StringToIpPortConverter();
        IpPort result = cv.convert("127.0.0.1:8080");
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
    }

}
