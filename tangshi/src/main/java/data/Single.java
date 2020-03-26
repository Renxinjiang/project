package data;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import common.JavaImageServerException;
import dao.DBUtil;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: dao
 * @ClassName: Single
 * @Description:
 * @author: 呆呆
 * @date: 2020/3/23
 */
public class Single {
    public static void main(String[] args) throws IOException {
        WebClient client = new WebClient(BrowserVersion.CHROME);//BrowserVersion.CHROME选择版本
        client.getOptions().setJavaScriptEnabled(false);//Css和JS都不需要让其执行，执为false
        client.getOptions().setCssEnabled(false);
        String baseUrl = "https://so.gushiwen.org";
        String pathUrl = "/gushi/poetry.aspx";

        List<String> detailUrlList = new ArrayList<>();

        //列表页解析
        {
            String url = baseUrl + pathUrl;
            HtmlPage page = client.getPage(url);
            List<HtmlElement> divs = page.getBody().getElementsByAttribute("div", "class", "typecont");
            for (HtmlElement div : divs) {//一个一个是div元素
                List<HtmlElement> as = div.getElementsByTagName("a");
                for (HtmlElement a : as) {
                    String detailUrl = a.getAttribute("href");
                    detailUrlList.add(baseUrl + detailUrl);
                }
            }
        }

        //详情页的请求和解析
        for (String url : detailUrlList) {
            HtmlPage page = client.getPage(url);
            String xpath;
            DomText domText;
            xpath = "//div[@class='cont']/h1/text()";
            domText = (DomText) page.getBody().getByXPath(xpath).get(0);
            String title = domText.asText();

            xpath = "//div[@class='cont']/p[@class='source']/a[1]/text()";
            domText = (DomText) page.getBody().getByXPath(xpath).get(0);
            String dynasty = domText.asText();

            xpath = "//div[@class='cont']/p[@class='source']/a[2]/text()";
            domText = (DomText) page.getBody().getByXPath(xpath).get(0);
            String author = domText.asText();

            xpath = "//div[@class='cont']/div[@class='contson']";
            HtmlElement element = (HtmlElement) page.getByXPath(xpath).get(0);

            String content = element.getTextContent().trim();//trim()：去掉空格

            //1、计算sha-256
            // 获取sha256对象
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            String s = title + content;
            messageDigest.update(s.getBytes("UTF-8"));
            byte[] result = messageDigest.digest();//存结果
            StringBuilder sha256 = new StringBuilder();
            for (byte b : result) {
                sha256.append(String.format("%02x", b));
            }
            //计算分词
            List<Term> termList = new ArrayList<>();
            termList.addAll(NlpAnalysis.parse(title).getTerms());
            termList.addAll(NlpAnalysis.parse(content).getTerms());
            List<String> words = new ArrayList<>();
            for (Term term : termList) {//一个Terms就是一个单词
                if (term.getNatureStr().equals("w")) {//特殊字符
                    continue;
                }
                if (term.getNatureStr().equals("null")) {
                    continue;
                }
                if (term.getRealName().length() < 2) {
                    continue;
                }
                words.add(term.getRealName());
            }
            String inserWords = String.join(",", words);

            // 1. 获取数据库连接
            Connection connection = DBUtil.getConnection();
            // 2. 创建并拼装 SQL 语句
            String sql = "INSERT INTO t_tangshi (sha256, dynasty, title, author, content, words) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, sha256.toString());
                statement.setString(2, dynasty);
                statement.setString(3, title);
                statement.setString(4, author);
                statement.setString(5, content);
                statement.setString(6, inserWords);
                // 3. 执行 SQL 语句
                int ret = statement.executeUpdate();
                if (ret != 1){
                    // 程序出现问题, 抛出一个异常
                    throw new JavaImageServerException("插入数据库出错!");
                }
            } catch (SQLException | JavaImageServerException e) {
                e.printStackTrace();
            }finally {
                // 4. 关闭连接和statement对象,放在finally中，避免未被执行到
                DBUtil.close(connection,statement,null);
            }
            System.out.println(title+"插入成功");
        }
    }
}
