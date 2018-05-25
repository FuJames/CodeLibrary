package code.library.utils;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author fuqianzhong
 * @date 18/5/2
 */
public class NetUtil {
    public static int getAvailablePort(int defaultPort) {
        int port = defaultPort;
        while (port < 65535) {
            if (!isPortInUse(port)) {
                return port;
            } else {
                port++;
            }
        }
        while (port > 0) {
            if (!isPortInUse(port)) {
                return port;
            } else {
                port--;
            }
        }
        throw new IllegalStateException("no available port");
    }

    public static boolean isPortInUse(int port) {
        boolean inUse = false;
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);
            inUse = false;
        } catch (IOException e) {
            inUse = true;
        } finally {
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                }
            }
        }
        return inUse;
    }
}
