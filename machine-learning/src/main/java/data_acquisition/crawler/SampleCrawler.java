package data_acquisition.crawler;

import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class SampleCrawler extends WebCrawler {
	private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");
	
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		
		if (IMAGE_EXTENSIONS.matcher(href).matches()) {
			return false;
		}
		
		return href.startsWith("https://en.wikipedia.org/wiki/");
	}
	
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		
		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			
			if (text.contains("shipping route")) {
				System.out.println("\nURL: " + url);
				System.out.println("Text: " + text);
				System.out.println("Text length: " + text.length());
			}
		}
	}
}
