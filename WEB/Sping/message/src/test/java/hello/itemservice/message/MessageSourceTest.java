package hello.itemservice.message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.isA;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage(){
        String result = ms.getMessage("hello", null,null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessage(){
        assertThatThrownBy(()->ms.getMessage("no", null,null))
            .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage() {
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    void argumentMessage(){
        String result = ms.getMessage("hello.name",new Object[]{"spring","age"}, null);
        assertThat(result).isEqualTo("안녕 spring");
    }

    @Test
    void defaultLang() {
        assertThat(ms.getMessage("hello", null,null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null,Locale.ENGLISH)).isEqualTo("hello");
    }

}
