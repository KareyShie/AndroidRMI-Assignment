package Tasks;
import models.ToyModel;

public class UpdateToyPrice {
    private boolean updateToyPrice(int newPrice, int id){
        ToyModel toyModel = new ToyModel();
        return toyModel.updateToyPrice(newPrice,id);
    }
}
