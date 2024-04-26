package com.example.SpringPlayground;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/*
 * Controller는 Android retrofit의 baseUrl을 지정해주듯이 RequestMaping을 지정해줄 수 있음
 */
@RestController
@RequestMapping("/coffees")
public class RestApiDemoController {
    private List<Coffee> coffees = new ArrayList<>();

    public RestApiDemoController() {
        coffees.addAll(List.of(
            new Coffee("Cafe Cereza"),
            new Coffee("Cafe Genador"),
            new Coffee("Cafe Lareno"),
            new Coffee("Cafe Tres Pontas")
        ));
    }

    @GetMapping
    Iterable<Coffee> getCoffees() {
        return coffees;
    }
    
    /**
     * NPE를 방지하기위해 Optional이라는 타입을 지정함.
     * 해당 타입은 wrapper 타입으로 empty를 지정해줄 수 있음
     */

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        for (Coffee c : coffees) {
            if(c.getId().equals(id)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }
    
    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee) {
        coffees.add(coffee);
        return coffee;
    }

    /*
     * PUT 메서드 응답의 경우 상태 코드는 필수임
     */
    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
        int coffeeIndex = -1;

        for (Coffee c : coffees) {
            if (c.getId().equals(id)) {
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }

        return (coffeeIndex == -1) ? new ResponseEntity<>(postCoffee(coffee),HttpStatus.CREATED) : new ResponseEntity<>(coffee,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffees.removeIf(c -> c.getId().equals(id));
    }
}
