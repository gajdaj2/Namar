import example.HttpAction;
import example.HttpActionMessageBuilder;
import manage.Manager;
import model.IAction;

public class Main {

    public static void main(String[] args) {
        IAction a = new HttpAction();
        IAction b = new HttpActionMessageBuilder();

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
