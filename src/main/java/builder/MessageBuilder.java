package builder;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import time.TimeExecution;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class MessageBuilder {


    public URIBuilder builder;
    public HashMap<String, String> parameters;
    public URI uri;
    public HttpGet get;
    public HttpPost post;

    public MessageBuilder createMessageWithProtocol(HttpMethod method) {
        switch (method) {
            case HTTP:
                builder = new URIBuilder().setScheme("http");
                break;
            case HTTPS:
                builder = new URIBuilder().setScheme("https");
                break;
        }
        return this;
    }

    public MessageBuilder andHostName(String host) {
        builder.setHost(host);
        return this;
    }

    public MessageBuilder wherePath(String path) {
        builder.setPath(path);
        return this;
    }

    public MessageBuilder addParameter(String key, String value) {
        if (parameters == null) {
            parameters = new HashMap<String, String>();
            parameters.put(key, value);
        } else {
            parameters.put(key, value);
        }
        return this;
    }

    public MessageBuilder buildMessage() {
        try {
            uri = builder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return this;
    }

    public MessageBuilder andUseHttpGet() {
        get = new HttpGet(uri);
        return this;
    }

    public CloseableHttpResponse getResponse() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
