package code.library.hash;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Map;

/**
 * @author fuqianzhong
 * @date 19/1/14
 * 一致性哈希算法,保证同一个key对应相同的hash值;不同key对应的hash值均匀分布在hash环上;
 */
public class HashUtils {

    public static int getHashFnv(String key){
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;

    }

    //相对FNV,Ketama更加均匀
    public static int getHashKetama(String key){

        byte[] md5 = DigestUtils.md5(key.getBytes());

        /** Md5是一个16字节长度的数组，将16字节的数组每四个字节一组*/
        int h = 0;
        Integer hash = ((md5[3+h*4]&0xFF) << 24)
                | ((md5[2+h*4]&0xFF) << 16)
                | ((md5[1+h*4]&0xFF) << 8)
                | (md5[h*4]&0xFF);
        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

    //测试:3台server,每台server对应10台虚拟节点
    public static void main(String[] args) {
        List<String> servers = Lists.newArrayList();
        servers.add("192.168.0.1");
        servers.add("192.168.0.1_V1");
        servers.add("192.168.0.1_V2");
        servers.add("192.168.0.1_V3");
        servers.add("192.168.0.1_V4");
        servers.add("192.168.0.1_V5");
        servers.add("192.168.0.1_V6");
        servers.add("192.168.0.1_V7");
        servers.add("192.168.0.1_V8");
        servers.add("192.168.0.1_V9");
        servers.add("192.168.0.1_V10");
        servers.add("192.168.0.2");
        servers.add("192.168.0.2_V1");
        servers.add("192.168.0.2_V2");
        servers.add("192.168.0.2_V3");
        servers.add("192.168.0.2_V4");
        servers.add("192.168.0.2_V5");
        servers.add("192.168.0.2_V6");
        servers.add("192.168.0.2_V7");
        servers.add("192.168.0.2_V8");
        servers.add("192.168.0.2_V9");
        servers.add("192.168.0.2_V10");
        servers.add("192.168.0.3");
        servers.add("192.168.0.3_V1");
        servers.add("192.168.0.3_V2");
        servers.add("192.168.0.3_V3");
        servers.add("192.168.0.3_V4");
        servers.add("192.168.0.3_V5");
        servers.add("192.168.0.3_V6");
        servers.add("192.168.0.3_V7");
        servers.add("192.168.0.3_V8");
        servers.add("192.168.0.3_V9");
        servers.add("192.168.0.3_V10");


        System.out.println(">>>>>>Hash FNV_32<<<<<<");
        boolean flag = true;
        Map<Integer ,Boolean> map = Maps.newHashMap();
        for(String server : servers){
            int key = getHashFnv(server);
            if(map.get(key) != null){
                flag = false;
                map.put(key,true);
            }else {
                map.put(key,true);
            }
            System.out.println((double)key/Integer.MAX_VALUE);
        }
        if(flag){
            System.out.println("FNV_32 Has No Conflicts");
        }else {
            System.out.println("FNV_32 Has Conflicts");
        }

        System.out.println(">>>>>>Hash Ketama<<<<<<");
        boolean flag2 = true;
        Map<Integer ,Boolean> map2 = Maps.newHashMap();
        for(String server : servers){
            int key = getHashKetama(server);
            if(map2.get(key) != null){
                flag2 = false;
                map2.put(key,true);
            }else {
                map2.put(key,true);
            }
            System.out.println((double)key/Integer.MAX_VALUE);
        }
        if(flag2){
            System.out.println("Ketama Hash No Conflicts");
        }else {
            System.out.println("Ketama Hash Conflicts");
        }

    }

}
