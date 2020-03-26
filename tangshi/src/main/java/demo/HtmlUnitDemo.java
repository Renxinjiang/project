package demo;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HtmlUnitDemo {
    public static void main(String[] args) throws IOException {
        // 无界面的浏览器(HTTP 客户端)
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        // 关闭了浏览器的 js 执行引擎，不再执行网页中的 js 脚本
        webClient.getOptions().setJavaScriptEnabled(false);
        // 关闭了浏览器的 css 执行引擎，不再执行网页中的 css 布局
        webClient.getOptions().setCssEnabled(false);
        HtmlPage page = webClient.getPage("https://so.gushiwen.org/gushi/poetry.aspx");
        System.out.println(page);
        File file = new File("唐诗三百首//列表页.html");
        file.delete();
        page.save(file);

        // 如何从 html 中提取我们需要的信息
        HtmlElement body = page.getBody();

        List<HtmlElement> elements = body.getElementsByAttribute(
                "div",
                "class",
                "typecont");


       /*
        for (HtmlElement element : elements) {
            System.out.println(element);
        }
        */

        HtmlElement divElement = elements.get(0);
        List<HtmlElement> aElements = divElement.getElementsByAttribute("a", "target", "_blank");
        for (HtmlElement e : aElements) {
            System.out.println(e);
        }
        System.out.println(aElements.size());
        System.out.println(aElements.get(0).getAttribute("href"));
    }
}
