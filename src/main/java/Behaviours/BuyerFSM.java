package Behaviours;

import jade.core.behaviours.FSMBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;

public class BuyerFSM extends FSMBehaviour {
    private static final String RECEIVE = "Send request";
    private static final String SUCCESS = "Success response";
    private static final String IGNORE = "Ignore response";
    private static final String REFUSE = "Refuse response";

    public BuyerFSM() {
        List<ACLMessage> answers = new ArrayList<>();
        registerFirstState(new BuyerRequestAndWaitResponse(myAgent, 5000, answers), RECEIVE);

        registerLastState(new BuyerReceiveSuccess(answers), SUCCESS);
        registerLastState(new BuyerReceiveRefuse(answers), REFUSE);
        registerLastState(new BuyerReceiveIgnore(), IGNORE);

        registerTransition(RECEIVE, SUCCESS, 0);
        registerTransition(RECEIVE, REFUSE, 1);
        registerTransition(RECEIVE, IGNORE, 2);

    }
}
