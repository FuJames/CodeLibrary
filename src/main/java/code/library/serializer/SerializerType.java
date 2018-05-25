package code.library.serializer;

/**
 * @author fuqianzhong
 * @date 18/5/16
 */
public enum SerializerType {
    JAVA((byte) 1, "java"),

    HESSIAN((byte) 2, "hessian"),

    PROTOBUF((byte) 9, "protobuf"),

    THRIFT((byte) 10, "thrift");

    private byte code;
    private String name;

    SerializerType(byte code, String name) {
        this.code = code;
        this.name = name;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
