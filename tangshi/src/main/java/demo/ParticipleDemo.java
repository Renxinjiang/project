package demo;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.util.List;

public class ParticipleDemo {
    public static void main(String[] args) {
        String sentence = "中华人民共和国成立了！中国人民从此站起来了！";
        List<Term> termList = NlpAnalysis.parse(sentence).getTerms();//NLP：自然语言处理
        for (Term term : termList) {//一个Terms就是一个单词
            System.out.println(term.getNatureStr() + ":" + term.getRealName());//词性+词名
        }
    }
}
