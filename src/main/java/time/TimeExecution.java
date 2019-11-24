package time;

import java.sql.Time;
import java.util.HashMap;
import java.util.LinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimeExecution {
    private String name;
    private long timeStart;
    private long timeStop;
    private long executionTime;
    private final Logger LOG = LogManager.getLogger(getClass());

    public void ActionStart(String name){
        this.name = name;
        timeStart = System.currentTimeMillis();
    }

    public void EndTime(){
        timeStop = System.currentTimeMillis();
        executionTime = timeStop - timeStart;
        LOG.info("Execution Time = {}, from Action {}",executionTime,name);
    }


}
