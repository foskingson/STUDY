package study.itemservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();
    
    @AfterEach
    void testClearStore() {
        itemRepository.clearStore();
    }

    @Test
    void testFindAll() {
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000,20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> result = itemRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1,item2);
    }


    @Test
    void testSave() {
        Item item = new Item("itemA", 10000, 10);
        Item saveItem = itemRepository.save(item);
        assertThat(saveItem).isInstanceOf(Item.class);

        Item findItem = itemRepository.findById(item.getId());
        assertThat(saveItem).isEqualTo(findItem);
    }

    @Test
    void testUpdate() {
        Item item = new Item("itemA", 10000, 10);   
        itemRepository.save(item);

        Item updateItem = new Item("itemB", 20000,20);

        itemRepository.update(item.getId(), updateItem);
        assertThat(item.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(item.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(item.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}
