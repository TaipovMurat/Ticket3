package Behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestBehaviour extends Behaviour {

    private static int result = 10;
    private boolean fin;

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage condition = myAgent.receive(mt);
        if (condition != null){
            if (condition.getContent().equals("Accept")){
                result = 0;
            } else if (condition.getContent().equals("Refuse")){
                result = 1;
            } else {
                result = 2;
            }
            fin = true;
        }else {
            block();
        }
    }

    @Override
    public boolean done() {
        return fin;
    }

    @Override
    public int onEnd() {
        return result;
    }
}
