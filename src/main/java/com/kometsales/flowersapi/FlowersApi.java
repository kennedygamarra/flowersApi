package com.kometsales.flowersapi;

import java.util.Arrays;
import java.util.List;

public class FlowersApi {
    private static List<Flower> flowers = Arrays.asList(
            new Flower(1, "Rose", 1.4),
            new Flower(2, "Tulip", 1),
            new Flower(3, "Rosarious", 2),
            new Flower(4, "Lilium", 2.99),
            new Flower(5, "Girasol", 20.69)
    );
    public static List<Flower> getFlowers() {
        return flowers;
    }

    public static void setFlowers(List<Flower> flowers) {
        FlowersApi.flowers = flowers;
    }
}
