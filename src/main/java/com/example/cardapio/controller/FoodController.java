package com.example.cardapio.controller;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public FoodResponseDTO create(@RequestBody FoodRequestDTO body) {
        Food food = new Food(body);
        repository.save(food);
        return new FoodResponseDTO(food);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll() {
        return repository.findAll().stream().map(FoodResponseDTO::new).toList();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public FoodResponseDTO getById(@PathVariable Long id) {
        Food food = repository.findById(id).orElseThrow(() -> new RuntimeException("Food not found"));
        return new FoodResponseDTO(food);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public FoodResponseDTO update(@PathVariable Long id, @RequestBody FoodRequestDTO body) {
        Food food = repository.findById(id).orElseThrow(() -> new RuntimeException("Food not found"));
        food.setTitle(body.title());
        food.setPrice(body.price());
        food.setImage(body.image());
        repository.save(food);
        return new FoodResponseDTO(food);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
        return;
    }
}
