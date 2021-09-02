package com.example.mysecondapp;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface RMIInterface extends Remote {
    public String helloTo(String name) throws RemoteException;


    public  String addToy(Integer toyID, Integer toyPrice)throws RemoteException;

    public String getAllToys() throws RemoteException;

    public String getAllToysWithoutPrice() throws RemoteException;

    public String getAllToysWithPrice() throws RemoteException;

    boolean updateToyPrice(int price, int id)throws RemoteException;

    boolean addToyPrice(int price, int id)throws RemoteException;

    boolean deleteToyPrice(int id) throws RemoteException;
}
