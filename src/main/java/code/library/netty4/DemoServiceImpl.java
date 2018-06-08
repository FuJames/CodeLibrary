package code.library.netty4;

import code.library.spring.RpcService;
import org.springframework.stereotype.Service;

/**
 * @author fuqianzhong
 * @date 18/6/7
 */
@RpcService(serviceKey = "DemoService_Key")
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello() {
        return "DemoServiceImpl.sayHello";
    }
}
