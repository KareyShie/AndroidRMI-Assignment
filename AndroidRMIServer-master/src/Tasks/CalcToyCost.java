package Tasks;
import models.Toy;
import models.ToyModel;

public class CalcToyCost {
    private int toyCost(int id){
        ToyModel toyModel = new ToyModel();
        Toy toy = toyModel.findToyById(id);
        return toy.getPrice();
    }
}
