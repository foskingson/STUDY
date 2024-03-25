package hello.itemservice.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

public class MessageCodesResolverTest {
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void MessageCodesResolverObject(){
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String messageCode : messageCodes) {
            System.out.println(messageCode);
        }

        assertThat(messageCodes).containsExactly("required.item","required");
    }

    @Test
    void messageCodeResolverField(){
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item","itemName",Stirng.class);
        for (String messageCode : messageCodes) {
            System.out.println(messageCode);
        }

        assertThat(messageCodes).containsExactly("required.item.itemName","required.itemName","required.hello.itemservice.validation.Stirng","required");
    }
}
