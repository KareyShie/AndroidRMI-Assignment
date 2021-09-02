package Tasks;


import models.ToyModel;

public class AddToyPrice {
    private boolean AddToyPrice(int newPrice, int id){
        ToyModel toyModel = new ToyModel();
        return toyModel.updateToyPrice(newPrice,id);
    }
}
