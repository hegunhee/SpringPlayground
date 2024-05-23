package com.example.SpringPlayground.inflearn.jpa.shop.controller;

import com.example.SpringPlayground.inflearn.jpa.shop.domain.item.Book;
import com.example.SpringPlayground.inflearn.jpa.shop.domain.item.Item;
import com.example.SpringPlayground.inflearn.jpa.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {
        Book book = Book.createBook(form.getName(), form.getPrice(), form.getStockQuantity(), form.getAuthor(), form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsBn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    //itemId에 대해 권한이 있는지 체크를 해줘야함
    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") String itemId, @ModelAttribute("form") BookForm form) {
        Book book = Book.createBook(form.getName(), form.getPrice(), form.getStockQuantity(), form.getAuthor(), form.getIsbn());
        book.setId(form.getId());

        itemService.saveItem(book);
        return "redirect:/items";
    }
}
