package hello.itemservice.domain.item;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
// @ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000") jdk버전때문에 실행 안됨
public class Item {
    
    //@NotNull(groups = UpdateCheck.class)    // 수정시 요구사항 추가
    private Long id;

    //@NotBlank(message = "공백 X", groups = {SaveCheck.class,UpdateCheck.class})
    private String itemName;

    //@NotNull(groups = {SaveCheck.class,UpdateCheck.class})
    //@Range(min=1000, max = 1000000, groups = {SaveCheck.class,UpdateCheck.class})
    private Integer price;

    // @NotNull(groups = {SaveCheck.class,UpdateCheck.class})
    // @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}