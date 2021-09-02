package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ToyModel {

    public List<Toy> findAllToys(){
        List<Toy> toys = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = ConnectToDB.getConnection()
                    .prepareStatement("SELECT * FROM toys");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Toy toy = new Toy(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("price"));
                toys.add(toy);
            }
        }catch (Exception e){
            toys = null;
        }
        return toys;
    }

    public List<Toy> findAllToysWithoutPrice(){
        List<Toy> toys = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = ConnectToDB.getConnection()
                    .prepareStatement("SELECT * FROM toys WHERE price = 0 OR price IS NULL");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Toy toy = new Toy(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("price"));
                toys.add(toy);
            }
        }catch (Exception e){
            toys = null;
        }
        return toys;
    }

    public List<Toy> findAllToysWithPrice(){
        List<Toy> toys = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = ConnectToDB.getConnection()
                    .prepareStatement("SELECT * FROM toys WHERE price > 0");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Toy toy = new Toy(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("price"));
                toys.add(toy);
            }
        }catch (Exception e){
            toys = null;
        }
        return toys;
    }

    public Toy findToyById(int id){
        Toy toy = null;
        try{
            PreparedStatement preparedStatement = ConnectToDB.getConnection()
                    .prepareStatement("SELECT * FROM toys WHERE id = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                toy = new Toy(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("price"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return toy;
    }

    public boolean addToyPrice(int newPrice, int id){
        boolean result = true;
        try{
            PreparedStatement preparedStatement = ConnectToDB.getConnection()
                    .prepareStatement("UPDATE toys SET price = ? WHERE id = ?");
            preparedStatement.setInt(1,newPrice);
            preparedStatement.setInt(2,id);
            result = preparedStatement.executeUpdate() > 0;

        }catch (Exception e){
            e.printStackTrace();
            result = true;
        }
        return result;
    }

    public boolean deleteToyPrice(int id){
        boolean result = true;
        try{
            PreparedStatement preparedStatement = ConnectToDB.getConnection()
                    .prepareStatement("UPDATE toys SET price = 0 WHERE id = ?");
            preparedStatement.setInt(1,id);
            result = preparedStatement.executeUpdate() > 0;

        }catch (Exception e){
            e.printStackTrace();
            result = true;
        }
        return result;
    }

    public boolean updateToyPrice(int newPrice, int id){
        boolean result = true;
        try{
            PreparedStatement preparedStatement = ConnectToDB.getConnection()
                    .prepareStatement("UPDATE toys SET price = ? WHERE id = ?");
            preparedStatement.setInt(1,newPrice);
            preparedStatement.setInt(2,id);
            result = preparedStatement.executeUpdate() > 0;

        }catch (Exception e){
            e.printStackTrace();
            result = true;
        }
        return result;
    }
}
