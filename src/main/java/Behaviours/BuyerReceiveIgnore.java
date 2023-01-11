package Behaviours;

import Helpers.DFHelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class BuyerReceiveIgnore extends OneShotBehaviour {
    private List<AID> agents;
    private int result;

    @Override
    public void action() {
        log.info("Agent's request was ignored");
    }
    @Override
    public int onEnd() {
        ACLMessage condition = new ACLMessage(ACLMessage.INFORM);
        agents = DFHelper.findAgents(getAgent(), "Test");
        for (AID agent: agents){
            condition.addReceiver(agent);
        }
        condition.setContent("Ignore");
        getAgent().send(condition);
        return result;
    }
}
