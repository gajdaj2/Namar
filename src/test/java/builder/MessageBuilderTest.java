package builder;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.net.URISyntaxException;

public class MessageBuilderTest {

    private MessageBuilder messageBuilder;

    @BeforeMethod
    public void setUp() {
        messageBuilder = new MessageBuilder();
    }

    @Test
    public void testCreateMessageWithProtocolHttp() throws URISyntaxException {
        messageBuilder.createMessageWithProtocol(HttpMethod.HTTP);
        Assert.assertEquals(messageBuilder.builder.getScheme(), "http");
    }

    @Test
    public void testCreateMessageWithProtocolHttps() throws URISyntaxException {
        messageBuilder.createMessageWithProtocol(HttpMethod.HTTPS);
        Assert.assertEquals(messageBuilder.builder.getScheme(), "https");
    }

    @Test
    public void testAndHostName() throws URISyntaxException {
        messageBuilder.createMessageWithProtocol(HttpMethod.HTTP).andHostName("example.com");
        Assert.assertEquals(messageBuilder.builder.getHost(), "example.com");
    }

    @Test
    public void testWherePath() throws URISyntaxException {
        messageBuilder.createMessageWithProtocol(HttpMethod.HTTP).wherePath("/testPath");
        Assert.assertEquals(messageBuilder.builder.getPath(), "/testPath");
    }

    @Test
    public void testAddParameter() throws URISyntaxException {
        messageBuilder.createMessageWithProtocol(HttpMethod.HTTP).addParameter("key", "value");
        Assert.assertEquals(messageBuilder.parameters.get("key"), "value");
    }

    @Test
    public void testBuildMessage() throws URISyntaxException {
        messageBuilder.createMessageWithProtocol(HttpMethod.HTTP).andHostName("example.com").wherePath("/testPath").buildMessage();
        Assert.assertEquals(messageBuilder.uri.toString(), "http://example.com/testPath");
    }

    @Test
    public void testAndUseHttpGet() throws URISyntaxException {
        messageBuilder.createMessageWithProtocol(HttpMethod.HTTP).andHostName("example.com").wherePath("/testPath").buildMessage().andUseHttpGet();
        Assert.assertNotNull(messageBuilder.get);
        Assert.assertEquals(messageBuilder.get.getURI().toString(), "http://example.com/testPath");
    }

    @Test
    public void testGetResponse() throws URISyntaxException {
        messageBuilder.createMessageWithProtocol(HttpMethod.HTTP).andHostName("httpbin.org").wherePath("/get").buildMessage().andUseHttpGet();
        CloseableHttpResponse response = messageBuilder.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200); // Assumes httpbin.org is accessible
    }
}