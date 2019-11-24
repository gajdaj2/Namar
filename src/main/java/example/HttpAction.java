package example;

import model.IAction;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import time.TimeExecution;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpAction implements IAction {

    public void Execute() throws URISyntaxException, IOException {
        TimeExecution time = new TimeExecution();
        time.ActionStart("Prognoza pogody");
        //https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22
        URIBuilder builder = new URIBuilder();
        builder.setScheme("https").setHost("samples.openweathermap.org").setPath("/data/2.5/weather");
        builder.addParameter("q", "London");
        builder.addParameter("appid", "b6907d289e10d714a6e88b30761fae22");
        URI uri = builder.build();
        HttpGet get = new HttpGet(uri);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(get);
        time.EndTime();
    }
}
