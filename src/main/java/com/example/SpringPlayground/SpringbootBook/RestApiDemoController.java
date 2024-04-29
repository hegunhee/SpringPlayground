package com.example.SpringPlayground.SpringbootBook;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
    private final CoffeeRepository coffeeRepository;

    public RestApiDemoController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @GetMapping
    Iterable<Coffee> getCoffees() {
        return coffeeRepository.findAll();
    }

    /**
     * NPE를 방지하기위해 Optional이라는 타입을 지정함.
     * 해당 타입은 wrapper 타입으로 empty를 지정해줄 수 있음
     */

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable("id") String id) {
        return coffeeRepository.findById(id);
    }

    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    /*
     * PUT 메서드 응답의 경우 상태 코드는 필수임
     */
    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable("id") String id, @RequestBody Coffee coffee) {
        return (coffeeRepository.existsById(id))
                ? new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK)
                : new ResponseEntity<>(coffee, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable("id") String id) {
        coffeeRepository.deleteById(id);
    }
}

interface CoffeeRepository extends CrudRepository<Coffee, String> {
}

@Component
class DataLoader {
    private final CoffeeRepository coffeeRepository;
    public DataLoader(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @PostConstruct
    private void loadData() {
        coffeeRepository.saveAll(List.of(
            new Coffee("Cafe Cereza"),
            new Coffee("Cafe Genador"),
            new Coffee("Cafe Lareno"),
            new Coffee("Cafe Tres Pontas")
        ));
    }
}