package com.example.SpringPlayground.inflearn.jpa.shop.controller;

import com.example.SpringPlayground.inflearn.jpa.shop.domain.Member;
import com.example.SpringPlayground.inflearn.jpa.shop.domain.Order;
import com.example.SpringPlayground.inflearn.jpa.shop.domain.item.Item;
import com.example.SpringPlayground.inflearn.jpa.shop.repository.OrderSearch;
import com.example.SpringPlayground.inflearn.jpa.shop.service.ItemService;
import com.example.SpringPlayground.inflearn.jpa.shop.service.MemberService;
import com.example.SpringPlayground.inflearn.jpa.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch,Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        model.addAttribute("orderSearch", orderSearch);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
