package Agents;

import Behaviours.SellerBehaviour;
import Helpers.DFHelper;
import jade.core.Agent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
@Data
@Slf4j
public class SellerAgent extends Agent {

//    private int result = new Random().nextInt(3);
//    private int result;

    @Override
    protected void setup() {
        DFHelper.registerAgent(this,"Seller");
//        log.info("RESSSUUUULT: {}", result);
        addBehaviour(new SellerBehaviour());
    }
}
