package com.example.mysecondapp;

/**
 * Created by asif on 2/4/18.
 */

public interface RMIInterface {
    String addToy(Integer toyID, Integer toyPrice);

    String getAllToys();

    String getAllToysWithoutPrice();

    String getAllToysWithPrice();

    boolean updateToyPrice(int price, int id);
    boolean deleteToyPrice(int id);



}