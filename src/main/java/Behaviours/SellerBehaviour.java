package Behaviours;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Data
@Slf4j
public class SellerBehaviour extends Behaviour {
    public static int result = new Random().nextInt(3);
    ACLMessage msg = new ACLMessage(ACLMessage.CFP);
    private boolean sending;

    public SellerBehaviour(){
    }
    public SellerBehaviour(int result){
        this.result = result;
    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage receive = getAgent().receive(mt);
        if (receive != null){
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
