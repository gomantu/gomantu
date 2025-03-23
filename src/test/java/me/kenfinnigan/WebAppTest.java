package me.kenfinnigan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;

import io.quarkiverse.playwright.InjectPlaywright;
import io.quarkiverse.playwright.WithPlaywright;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@WithPlaywright
public class WebAppTest {
  @InjectPlaywright
  BrowserContext context;

  @TestHTTPResource("/")
  URL index;

  @Test
  public void testIndex() {
      final Page page = context.newPage();
      Response response = page.navigate(index.toString());
      assertEquals("OK", response.statusText());

      page.waitForLoadState();

      String title = page.title();
      assertEquals("GoMantu", title);
  }
}
