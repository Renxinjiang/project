package demo;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

public class DetailsDownloadExtractDemo {
    public static void main(String[] args) throws IOException {
        try (WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);

            String url = "https://so.gushiwen.org/shiwenv_45c396367f59.aspx";
            HtmlPage page = webClient.getPage(url);
            HtmlElement body = page.getBody();
            /*
            List<HtmlElement> elements = body.getElementsByAttribute(
                    "div",
                    "class",
                    "contson"
            );

            for (HtmlElement element : elements) {
                System.out.println(element);
            }

            System.out.println(elements.get(0).getTextContent().trim());
             */

            // 标题
            {
                String xpath = "//div[@class='cont']/h1/text()";
                Object o = body.getByXPath(xpath).get(0);//getByXPath(xpath)取出来是list类型，所以从第0个取
                DomText domText = (DomText)o;//Dom树
                System.out.println(domText.asText());//asText()：取到它
            }
            //朝代
            {
                String xpath = "//div[@class='cont']/p[@class='source']/a[1]/text()";//p标签下来a标签，直接从1开始没有0
                Object o = body.getByXPath(xpath).get(0);
                DomText domText = (DomText)o;
                System.out.println(domText.asText());
            }
            //作者
            {
                String xpath = "//div[@class='cont']/p[@class='source']/a[2]/text()";
                Object o = body.getByXPath(xpath).get(0);
                DomText domText = (DomText)o;
                System.out.println(domText.asText());
            }
            //正文
            {
                String xpath = "//div[@class='cont']/div[@class='contson']";
                Object o = body.getByXPath(xpath).get(0);
                HtmlElement element = (HtmlElement)o;//是元素不是Dom节点，所以用HtmlElement
                System.out.println(element.getTextContent().trim());//trim()：去掉空格
            }
        }
    }
}
