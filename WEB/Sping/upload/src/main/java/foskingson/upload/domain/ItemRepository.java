package foskingson.upload.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
    private final Map<Long,Item> store = new HashMap<>();   
    private long seq=0L;

    public Item save(Item item){
        item.setId(++seq);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        Item item = store.get(id);
        return item;
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }
}
