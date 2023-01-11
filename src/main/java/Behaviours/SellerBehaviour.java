package Behaviours;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
@Slf4j
public class SellerBehaviour extends Behaviour {

    private int result;
    ACLMessage msg = new ACLMessage(ACLMessage.CFP);
    private boolean sending;

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage receive = getAgent().receive(mt);
        if (receive != null){
            result = new Random().nextInt(3);
            if (result == 0){
                    msg.setPerformative(ACLMessage.PROPOSE);
                    msg.setContent("Accept your request");
                    log.info("Agent {} accept request", getAgent().getLocalName());
                    msg.addReceiver(receive.getSender());
                    getAgent().send(msg);
                }else if (result == 1) {
                    msg.setPerformative(ACLMessage.REFUSE);
                    msg.setContent("Refuse your request");
                    log.info("Agent {} refuse request", getAgent().getLocalName());
                    msg.addReceiver(receive.getSender());
                    getAgent().send(msg);
                }else {
                    log.info("Agent {} ignoring buyer", getAgent().getLocalName());
            }
            sending = true;
        }
    }

    @Override
    public boolean done() {
        return sending;
    }
}
