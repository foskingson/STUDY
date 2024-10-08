package foskingson.jpabook.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import foskingson.jpabook.domain.Delivery;
import foskingson.jpabook.domain.DeliveryStatus;
import foskingson.jpabook.domain.Member;
import foskingson.jpabook.domain.Order;
import foskingson.jpabook.domain.OrderItem;
import foskingson.jpabook.domain.item.Item;
import foskingson.jpabook.repository.ItemRepository;
import foskingson.jpabook.repository.MemberRepository;
import foskingson.jpabook.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    /** 주문 */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);
        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(),
                count);
        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    /** 주문 취소 */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }
    /** 주문 검색 */
    /*
     * public List<Order> findOrders(OrderSearch orderSearch) {
     * return orderRepository.findAll(orderSearch);
     * }
     */
}
