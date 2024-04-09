package foskingson.tybeconverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import foskingson.tybeconverter.converter.IntegerToStringConverter;
import foskingson.tybeconverter.converter.IpPortToStringConverter;
import foskingson.tybeconverter.converter.StringToIntegerConverter;
import foskingson.tybeconverter.converter.StringToIpPortConverter;
import foskingson.tybeconverter.formatter.MyNumberFormatter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // registry.addConverter(new StringToIntegerConverter());
        // registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        registry.addFormatter(new MyNumberFormatter());
    }
}
