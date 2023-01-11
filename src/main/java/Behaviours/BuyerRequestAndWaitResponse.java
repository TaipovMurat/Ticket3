package Behaviours;

import Helpers.DFHelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class BuyerRequestAndWaitResponse extends ParallelBehaviour {

    private long timeout;
    List<AID> agents = new ArrayList<>();
    private int result;
    private List<ACLMessage> answers;

    public BuyerRequestAndWaitResponse(Agent a, long timeout, List<ACLMessage> answers){
        super(a, WHEN_ANY);
        this.timeout = timeout;
        this.answers = answers;

    }

    @Override
    public void onStart() {
        WakerBehaviour waker = new WakerBehaviour(myAgent, this.timeout) {
            @Override
            protected void onWake() {
                log.info("Time is over, buyer didn't get response");
                result = 2;
            }
        };

        Behaviour buyer = new Behaviour() {
            MessageTemplate mt = MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.PROPOSE),
                    MessageTemplate.MatchPerformative(ACLMessage.REFUSE));

            @Override
            public void onStart() {
                agents = DFHelper.findAgents(getAgent(),"Seller");

                ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                for (AID agent: agents){
                    request.addReceiver(agent);
                    log.info("Agent {} send his request to {}", getAgent().getLocalName(), agent.getLocalName());
                }
                request.setContent("Hi! It's my request");
                getAgent().send(request);
            }

            @Override
            public void action() {
                ACLMessage receive = getAgent().receive(mt);
                if (receive != null){
                    if (receive.getPerformative() == ACLMessage.PROPOSE){
                        result = 0;
                        answers.add(receive);
                        log.info("Agent {} got propose from {}", getAgent().getLocalName(),
                                receive.getSender().getLocalName());
                    }else {
                        result = 1;
                        answers.add(receive);
                        log.info("Agent {} got refuse from {}", getAgent().getLocalName(),
                                receive.getSender().getLocalName());
                    }
                }
            }

            @Override
            public boolean done() {
                return answers.size() == agents.size();
            }
        };

        addSubBehaviour(waker);
        addSubBehaviour(buyer);
    }

    @Override
    public int onEnd() {
        log.info("The result of trading is: {}", result);
        return result;
    }
}
