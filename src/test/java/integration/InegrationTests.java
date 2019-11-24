package integration;

import example.HttpAction;
import example.HttpActionMessageBuilder;
import manage.Manager;
import model.IAction;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class InegrationTests {

    private IAction a;
    private IAction b;

    @BeforeTest
    public void init(){
        a = new HttpAction();
        b = new HttpActionMessageBuilder();
    }

   @Test
    public void TestPeformance(){
        Manager manager2 = new Manager();
        manager2.setNumberOfActions(5,b);
        manager2.setNumberOfThread(3);

        Manager manager1 = new Manager();
        manager1.setNumberOfActions(5, a);
        manager1.setNumberOfThread(3);

        manager1.runTest();
        manager2.runTest();
    }



}

