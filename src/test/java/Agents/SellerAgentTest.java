package Agents;

import Behaviours.*;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SellerAgentTest extends SetAgents{

    @Test
    @SneakyThrows
    void AcceptTest(){
        List<String> services = new ArrayList<>();
        services.add("jade.core.event.NotificationService");
        startJade(services);


        SellerBehaviour sellerBehaviour = new SellerBehaviour(0);

        BuyerFSM buyerFSM = new BuyerFSM();

        createSellerAgent("Seller", sellerBehaviour);

        createBuyerAgent("Buyer", buyerFSM);

        TestBehaviour testBehaviour = new TestBehaviour();
        createTestAgent("Test", testBehaviour);

//        WakerBehaviour wakerBehaviour = new WakerBehaviour() {
//            @Override
//            protected void onWake() {
//                super(myAgent, 1000);
//            }
//        };
        Thread.sleep(1000);

        Assertions.assertEquals(0, testBehaviour.onEnd());
    }

    @Test
    @SneakyThrows
    void RefuseTest(){
        List<String> services = new ArrayList<>();
        services.add("jade.core.event.NotificationService");
        startJade(services);


        SellerBehaviour sellerBehaviour = new SellerBehaviour(1);

        BuyerFSM buyerFSM = new BuyerFSM();

        createSellerAgent("Seller", sellerBehaviour);

        createBuyerAgent("Buyer", buyerFSM);

        TestBehaviour testBehaviour = new TestBehaviour();
        createTestAgent("Test", testBehaviour);

//        WakerBehaviour wakerBehaviour = new WakerBehaviour() {
//            @Override
//            protected void onWake() {
//                super(myAgent, 1000);
//            }
//        };
        Thread.sleep(1000);

        Assertions.assertEquals(1, testBehaviour.onEnd());
    }

    @Test
    @SneakyThrows
    void IgnoreTest(){
        List<String> services = new ArrayList<>();
        services.add("jade.core.event.NotificationService");
        startJade(services);


        SellerBehaviour sellerBehaviour = new SellerBehaviour(2);

        BuyerFSM buyerFSM = new BuyerFSM();

        createSellerAgent("Seller", sellerBehaviour);

        createBuyerAgent("Buyer", buyerFSM);

        TestBehaviour testBehaviour = new TestBehaviour();
        createTestAgent("Test", testBehaviour);

//        WakerBehaviour wakerBehaviour = new WakerBehaviour() {
//            @Override
//            protected void onWake() {
//                super(myAgent, 1000);
//            }
//        };
        Thread.sleep(10500);

        Assertions.assertEquals(2, testBehaviour.onEnd());
    }

}