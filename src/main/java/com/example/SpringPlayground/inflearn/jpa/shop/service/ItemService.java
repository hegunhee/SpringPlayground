package com.example.SpringPlayground.inflearn.jpa.shop.service;

import com.example.SpringPlayground.inflearn.jpa.shop.domain.item.Book;
import com.example.SpringPlayground.inflearn.jpa.shop.domain.item.Item;
import com.example.SpringPlayground.inflearn.jpa.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional // 이 어노테이션이 있는 함수에서 해야 영속상태 가능
    public void updateItem(Long itemId,String name,int price,int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId); // 이제는 영속상태
        findItem.change(name,price,stockQuantity);

        //영속상태이므로 값을 세팅하고 merge할 필요 없음

    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
