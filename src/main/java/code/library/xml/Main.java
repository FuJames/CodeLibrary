package code.library.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuqianzhong on 17/9/12.
 */
public class Main {
    public static void main(String[] args) {
        /**
         * DomXmlParser 测试
         */
//        DomXmlParser domXmlParser = new DomXmlParser(Constant.TEST_XML_PATH);
//        Node node = domXmlParser.evalNode("/mapper");
//        Properties properties = domXmlParser.parseAttributes(node);
//        System.out.println(properties);
//        node.getNamespaceURI();
//
//        NodeList children = node.getChildNodes();
//        for(int i = 0;i < children.getLength(); ++i){
//            Node child = children.item(i);
//
//        }

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
//        doList(list,5);
        doList(list,0);
        System.out.println(list);


    }

    public static void doList(List<Integer> list,int max){
        int i = 0;
        for(int j = 0;j < list.size() ; j++ ){
            if(i >= max){
                list.remove(j);
                j--;
            }
            i ++;
        }
//        list = list.subList(0,5);
    }


}
