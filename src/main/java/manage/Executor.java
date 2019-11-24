package manage;

import model.IAction;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;

public class Executor implements Runnable {

    private LinkedList<IAction> listOfAction;

    public void addAction(IAction a) {
        if (listOfAction == null) {
            listOfAction = new LinkedList<IAction>();
            listOfAction.add(a);
        } else {
            listOfAction.add(a);
        }
    }

    public void run() {
        for (IAction action : listOfAction) {
            try {
                action.Execute();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
