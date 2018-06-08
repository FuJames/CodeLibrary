package code.library.spring;

import code.library.netty4.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * @author fuqianzhong
 * @date 18/6/7
 */
public class ClientTester {
    public static void main(String[] args) {
        //启动服务
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/rpc-client.xml");
        DemoService demoService = (DemoService) ctx.getBean("demoServiceRpc");
        System.out.println("Result Is:" + demoService.sayHello());

        DemoService demoService2 = (DemoService) ctx.getBean("demoServiceRpc2");
        System.out.println("Result Is:" + demoService2.sayHello());
        DemoService demoService3 = (DemoService) ctx.getBean("demoServiceRpc3");
        System.out.println("Result Is:" + demoService3.sayHello());
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
    }
}
