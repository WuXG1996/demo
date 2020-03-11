package com.example.demo.other;

import com.example.demo.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by void on 2019/8/29.
 */
public class Dom4jTest {

    /**
     * 格式化xml
     * @param document
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    private String formatXml(Document document) throws DocumentException, IOException {
        StringWriter writer = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter xmlwriter = new XMLWriter( writer, format );
        try {
            xmlwriter.write(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    /**
     * xml构建
     * @throws IOException
     * @throws DocumentException
     */
    @Test
    public void test1() throws IOException, DocumentException {
        Document document = DocumentHelper.createDocument();

        Element root = document.addElement("test");
        root.addAttribute("header", "100");
        root.addAttribute("version", "1.0");
        root.addAttribute("security", "none");

        Element requestHeader = root.addElement("requestHeader");
        requestHeader.addElement("language").addText("UTF-8");
        requestHeader.addElement("appId").addText("1111111");
        requestHeader.addElement("appSecret").addText("2222");

        Element xDataBody = root.addElement("xDataBody");
        xDataBody.addElement("useName").addText("void");
        xDataBody.addElement("date").addText(DateUtils.date2Str(new Date(), DateUtils.date_sdf));
        xDataBody.addElement("sex").addText("男");

        System.out.println(this.formatXml(document));
    }

    /**
     * xml解析
     */
    @Test
    public void test2(){
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\n" +
                "<test header=\"100\" version=\"1.0\" security=\"none\">\n" +
                "    <requestHeader>\n" +
                "        <language>UTF-8</language>\n" +
                "        <appId>1111111</appId>\n" +
                "        <appSecret>2222</appSecret>\n" +
                "    </requestHeader>\n" +
                "    <xDataBody>\n" +
                "        <useInfo>\n" +
                "            <useName>void</useName>\n" +
                "            <date>2019-08-29</date>\n" +
                "            <sex>男</sex>\n" +
                "        </useInfo>\n" +
                "        <useInfo>\n" +
                "            <useName>gg</useName>\n" +
                "            <date>2019-08-28</date>\n" +
                "            <sex>女</sex>\n" +
                "        </useInfo>\n" +
                "    </xDataBody>\n" +
                "</test>";
        try{
            Document doc = DocumentHelper.parseText(xmlStr);
            Element rootElt = doc.getRootElement();
            //获取节点文档的2种方式
            Element requestHeader = rootElt.element("requestHeader");
            System.out.println(requestHeader.elementText("appId"));

            Element appId = requestHeader.element("appId");
            System.out.println(appId.getText());

            Element xDataBody  = rootElt.element("xDataBody");
            List<Element> userInfoList = xDataBody.elements("useInfo");
            for (Element element : userInfoList) {
                System.out.println(element.elementText("useName"));
                System.out.println(element.elementText("date"));
                System.out.println(element.elementText("sex"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
