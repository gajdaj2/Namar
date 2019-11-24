package model;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IAction {
    void Execute() throws URISyntaxException, IOException;
}
