package com.kometsales.flowersapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class FlowersController {

    /**
     * This method is used to set the list of flowers
     * @param flowers
     */
    @PostMapping("/newFlowers")
    public String setFlowers(@RequestBody List<Flower> flowers) {
       try {
           FlowersApi.setFlowers(flowers);
           return "Flowers added successfully";
         } catch (Exception e) {
           e.printStackTrace();
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error", e);
         }
    }

    /**
     * This function is used to get the list of name and price of flowers
     * @return List of flowers
     */
    @GetMapping("/getFlowersNameAndPrice")
    public List<Map<String,Object>> getFlowersNameAndPrice() {
        List<Flower> flowers = FlowersApi.getFlowers();

        flowers.sort((flower1, flower2) -> flower2.getName().compareTo(flower1.getName()));

        return flowers
                .stream()
                .map(flower -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", flower.getName()+"-kometsales");
                    map.put("price", flower.getPrice());
                    return map;
                }).collect(Collectors.toList());
    }

    /**
     * This function is used to get the list of flowers with price greater than 20
     * @return List of flowers
     */
    @GetMapping("/getFlowersPriceGreaterThan20")
    public List<Flower> getFlowersPriceGreaterThan20() {
        List<Flower> flowers = FlowersApi.getFlowers();

        return flowers
                .stream()
                .filter(flower -> flower.getPrice() > 20)
                .collect(Collectors.toList());
    }

    /**
     * This function is used to remove one flower from the list
     * @return List of flowers
     */
    @DeleteMapping("/removeFlowerById")
    public ResponseEntity<Void> removeFlowerById(@RequestParam int id) {

        List<Flower> flowers = FlowersApi.getFlowers()
                .stream()
                .filter(flower -> flower.getId() != id)
                .collect(Collectors.toList());

        FlowersApi.setFlowers(flowers);
        return ResponseEntity.noContent().build();
    }

    /**
     * This function is find flowers by name and return the list of flowers
     * @return List of flowers
     */
    @GetMapping("/findFlowersByName")
    public List<Flower> findFlowersByName(@RequestParam String name) {
        List<Flower> flowers = FlowersApi.getFlowers();

        List<Flower> result = flowers
                .stream()
                .filter(flower -> flower.getName().toLowerCase().contains(name.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());

            return result;
    }

}
