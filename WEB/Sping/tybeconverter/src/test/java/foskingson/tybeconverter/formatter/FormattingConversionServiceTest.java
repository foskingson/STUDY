package foskingson.tybeconverter.formatter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import foskingson.tybeconverter.converter.IpPortToStringConverter;
import foskingson.tybeconverter.converter.StringToIntegerConverter;
import foskingson.tybeconverter.converter.StringToIpPortConverter;
import foskingson.tybeconverter.type.IpPort;

public class FormattingConversionServiceTest {

    @Test
    void formatting컨버전서비스(){
        DefaultFormattingConversionService cv = new DefaultFormattingConversionService();
        //컨버터 등록
        cv.addConverter(new StringToIpPortConverter());
        cv.addConverter(new IpPortToStringConverter());
        //포맷터 등록
        cv.addFormatter(new MyNumberFormatter());
        //컨버터 사용
        IpPort ipPort = cv.convert("127.0.0.1:8080",IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));
        //포맷터 사용
        assertThat(cv.convert(1000, String.class)).isEqualTo("1,000");
        assertThat(cv.convert("1,000",Long.class)).isEqualTo(1000L);

    }
    
}
