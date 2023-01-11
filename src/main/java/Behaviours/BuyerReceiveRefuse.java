package Behaviours;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class BuyerReceiveRefuse extends OneShotBehaviour {

    List<ACLMessage> answers;

    public BuyerReceiveRefuse(List<ACLMessage> answers) {
        this.answers = answers;
    }

    @Override
    public void action() {
        ACLMessage inform = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
        for (ACLMessage answer: answers) {
            log.info("Accept refuse from {}", answer.getSender().getLocalName());
            inform.addReceiver(answer.getSender());
        }
        inform.setContent("Deal's refuse");
        getAgent().send(inform);

    }
}
