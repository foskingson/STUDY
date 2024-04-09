package foskingson.tybeconverter.formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyNumberFormatter implements Formatter<Number> {
    @Override
    public String print(Number object, Locale locale) {
        log.info("object={}, locale={}",object,locale);
        NumberFormat instance = NumberFormat.getInstance(locale);
        return instance.format(object);
    }
    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        log.info("text={} , locale={}",text,locale);
        NumberFormat format = NumberFormat.getInstance(locale);
        Number parse = format.parse(text);
        return parse;
    }
}
