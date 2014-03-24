import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;

import java.util.Collections;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import play.mvc.Content;
import play.mvc.Http;
import play.mvc.Http.Request;
import play.test.FakeApplication;
import play.test.Helpers;

import java.util.Map;
import java.util.Collections;
import org.junit.*;
import static org.mockito.Mockito.*;
import play.mvc.*;
import play.test.*;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.api.mvc.RequestHeader;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

	public static FakeApplication app;
	
	@BeforeClass
	public static void startApp() {
	  app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
	  Helpers.start(app);
	}

	@Test
    public void renderTemplate() {
		Map<String, String> flashData = Collections.emptyMap();
		Map<String, String> sessionData = Collections.emptyMap();
        Map<String, Object> argData = Collections.emptyMap();
        Long id = 2L;
        play.api.mvc.RequestHeader header = mock(play.api.mvc.RequestHeader.class);
        Request request = mock(Request.class); 
        Context context = mock(Context.class);
        Http.Context.current.set(context);
        
        Content html = views.html.index.render();
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("ObservaTerra"); 
    }

    @AfterClass
	  public static void stopApp() {
	    Helpers.stop(app);
	}
}
