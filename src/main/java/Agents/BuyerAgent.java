package Agents;

import Behaviours.BuyerFSM;
import Helpers.DFHelper;
import jade.core.Agent;

public class BuyerAgent extends Agent {
    @Override
    protected void setup() {
        DFHelper.registerAgent(this, "Buyer");
        addBehaviour(new BuyerFSM());
    }
}
