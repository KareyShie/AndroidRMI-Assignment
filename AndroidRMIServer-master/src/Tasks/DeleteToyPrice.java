package Tasks;

import models.ToyModel;

public class DeleteToyPrice {
    private boolean deleteToyPrice(int id){
        ToyModel toyModel = new ToyModel();
        return toyModel.updateToyPrice(0,id);
    }
}
