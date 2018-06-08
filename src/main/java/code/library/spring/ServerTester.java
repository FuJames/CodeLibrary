package code.library.spring;

import code.library.spring.factorybean.ServiceFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * @author fuqianzhong
 * @date 18/6/5
 */
public class ServerTester {
    public static void main(String[] args) {
        //启动服务
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/rpc-server.xml");
        ServiceFactory serviceFactory = (ServiceFactory) ctx.getBean("rpcServer");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
    }
}
