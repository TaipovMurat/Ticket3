package Agents;

import Behaviours.TestBehaviour;
import Helpers.DFHelper;
import jade.core.Agent;

public class TestAgent extends Agent {
    @Override
    protected void setup() {
        DFHelper.registerAgent(this, "Test");
        addBehaviour(new TestBehaviour());
    }
}
