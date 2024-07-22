package com.example.SpringPlayground.inflearn.spring.mvcweb.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository // 컴포넌트 스캔의 대상이 됨
public class ItemRepository {

    private static final Map<Long,Item> store = new HashMap<>(); // static 주의 멀티쓰레드의 경우에는 HashMap을 사용하면 안됨
    //ConcurrentHashMap 사용해야함
    private static long sequence = 0L; // 스태틱
    //AtomicLong 사용해야함

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(),item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
