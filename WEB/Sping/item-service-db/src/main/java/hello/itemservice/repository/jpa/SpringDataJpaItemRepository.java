package hello.itemservice.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.itemservice.domain.Item;

public interface SpringDataJpaItemRepository extends JpaRepository<Item,Long> { // 기본적인 CRUD는 인터페이스 상속받아서 이미 완성
    
    List<Item> findByItemNameLike(String itemName); // 상품명이 같은거만 찾기
    List<Item> findByPriceLessThanEqual(Integer price); // 해당 가격 이하만 찾기

    // 쿼리 메서드
    List<Item> findByItemNameLikeAndPriceLessThanEqual(String itemName,Integer price);

    // 쿼리 직접 실행
    @Query("select i from Item i where i.itemName like :itemName and i.price <= :price")
    List<Item> findItems(@Param("itemName") String itemName,@Param("price") Integer price); // @Param 꼭 넣어줘야 한다.
}
