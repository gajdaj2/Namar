package manage;

import model.IAction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class Manager {
    private ExecutorService service;
    private Executor exec;
    private List<Thread> list;

    public void setNumberOfActions(int a, IAction action) {
        exec = new Executor();
        for (int x = 0; x <= a; x++) {
            exec.addAction(action);
        }
    }

    public void setNumberOfThread(int a) {
        list = new ArrayList<Thread>();
        for (int x = 0; x <= a; x++) {
            list.add(new Thread(exec));
        }
    }

    public void runTest() {
        for (Thread x : list) {
            x.start();
        }
    }
}
