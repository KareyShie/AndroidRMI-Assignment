package rmi;

import com.google.gson.Gson;

import com.example.mysecondapp.RMIInterface;

import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.net.IServerListener;
import lipermi.net.Server;
import models.Toy;
import models.ToyModel;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ToyComputeEngine extends UnicastRemoteObject implements RMIInterface {

    public ToyComputeEngine() throws RemoteException {
        super();
        try {
            CallHandler callHandler = new CallHandler();
            callHandler.registerGlobal(RMIInterface.class, this);
            Server server = new Server();
            server.bind(AppConfig.SERVER_PORT, callHandler);
            server.addServerListener(new IServerListener() {

                @Override
                public void clientDisconnected(Socket socket) {
                    System.out.println("Client Disconnected: " + socket.getInetAddress());
                }

                @Override
                public void clientConnected(Socket socket) {
                    System.out.println("Client Connected: " + socket.getInetAddress());
                }
            });
            System.out.println("Server Listening");
        } catch (LipeRMIException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String helloTo(String name){
        System.err.println(name + " is trying to contact!");
        return "Server says hello to " + name;
    }


    @Override
    public String getAllToys(){
        ToyModel toyModel = new ToyModel();
        List<Toy> toys = toyModel.findAllToys();
        Gson gson = new Gson();
        String json = gson.toJson(toys);
        System.out.println(json);
        return json;
    }

    @Override
    public String getAllToysWithoutPrice(){
        ToyModel toyModel = new ToyModel();
        List<Toy> toys = toyModel.findAllToysWithoutPrice();
        Gson gson = new Gson();
        String json = gson.toJson(toys);
        System.out.println(json);
        return json;
    }

    @Override
    public String getAllToysWithPrice(){
        ToyModel toyModel = new ToyModel();
        List<Toy> toys = toyModel.findAllToysWithPrice();
        Gson gson = new Gson();
        String json = gson.toJson(toys);
        System.out.println(json);
        return json;
    }

    @Override
    public boolean updateToyPrice(int price, int id){
        ToyModel toyModel = new ToyModel();
        return toyModel.updateToyPrice(price,id);
    }

    @Override
    public boolean addToyPrice(int price, int id){
        ToyModel toyModel = new ToyModel();
        return toyModel.addToyPrice(price,id);
    }

    @Override
    public boolean deleteToyPrice(int id){
        ToyModel toyModel = new ToyModel();
        return toyModel.deleteToyPrice(id);

    }


    @Override
    public String addToy(Integer toyID, Integer toyPrice){
        return "Toy "+toyID+" with Price "+toyPrice+" has been added!";
    }

}