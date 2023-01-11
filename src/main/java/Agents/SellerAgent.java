package Agents;

import Behaviours.SellerBehaviour;
import Helpers.DFHelper;
import jade.core.Agent;

public class SellerAgent extends Agent {


    @Override
    protected void setup() {
        DFHelper.registerAgent(this,"Seller");
        addBehaviour(new SellerBehaviour());
    }
}
