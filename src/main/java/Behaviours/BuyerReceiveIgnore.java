package Behaviours;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyerReceiveIgnore extends OneShotBehaviour {
    @Override
    public void action() {
        log.info("Agent's request was ignored");
    }
}
