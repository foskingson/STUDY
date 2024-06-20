package hello.itemservice.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity // JPA가 사용하는 객체라는 뜻
public class Item {
    // @Id : 테이블의 PK와 해당 필드를 매핑한다 
    // @GeneratedValue(strategy = GenerationType.IDENTITY) : PK 생성 값을 데이터베이스에서 생성하는 IDENTITY 방식을 사용한다
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name = "item_name",length = 10) // @Column : 객체의 필드를 테이블의 컬럼과 매핑한다
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
