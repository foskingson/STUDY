package foskingson.jpabook.repository;

import foskingson.jpabook.domain.OrderStatus;
import lombok.Data;

@Data
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;

}
