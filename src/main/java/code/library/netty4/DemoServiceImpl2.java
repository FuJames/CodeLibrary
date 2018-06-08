package code.library.netty4;

import code.library.spring.RpcService;
import org.springframework.stereotype.Service;

/**
 * @author fuqianzhong
 * @date 18/6/7
 */
@RpcService(serviceKey = "DemoService2_Key")
@Service
public class DemoServiceImpl2 implements DemoService {
    @Override
    public String sayHello() {
        return "DemoServiceImpl2.sayHello";
    }
}
