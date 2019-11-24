package example;

import builder.HttpMethod;
import builder.MessageBuilder;
import model.IAction;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import time.TimeExecution;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpActionMessageBuilder implements IAction {
    public void Execute() throws URISyntaxException, IOException {
        TimeExecution time = new TimeExecution();
        time.ActionStart("Wirtualna Polska Transakcja:  ");
        MessageBuilder b = new MessageBuilder();
        CloseableHttpResponse sss = b.createMessageWithProtocol(HttpMethod.HTTP)
                .andHostName("wp.pl")
                .wherePath("/")
                .buildMessage()
                .andUseHttpGet()
                .getResponse();
        time.EndTime();
    }
}
