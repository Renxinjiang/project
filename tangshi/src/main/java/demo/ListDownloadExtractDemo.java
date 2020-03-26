package demo;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.List;

public class ListDownloadExtractDemo {
    public static void main(String[] args) throws IOException {
        //用try方法是为了在结束时直接调用close方法将其关闭掉
        try (WebClient webClient = new WebClient(BrowserVersion.CHROME)) {//BrowserVersion.CHROME选择版本
            webClient.getOptions().setJavaScriptEnabled(false);//Css和JS都不需要让其执行，执为false
            webClient.getOptions().setCssEnabled(false);
            String url = "https://so.gushiwen.org/gushi/poetry.aspx";
            HtmlPage page = webClient.getPage(url);//下载下来的放在HtmlPage中
            HtmlElement body = page.getBody();
            List<HtmlElement> elements = body.getElementsByAttribute(//取项
                    "div",
                    "class",
                    "typecont"
            );

            int count = 0;
            for (HtmlElement element : elements) {//一个一个是div元素
                List<HtmlElement> aElements = element.getElementsByTagName("a");//取出div中的a标签
                for (HtmlElement a : aElements) {
                    System.out.println(a.getAttribute("href"));//取a中的属性href
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
