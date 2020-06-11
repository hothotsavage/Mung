package site.marqstree.mung.enums;

//后端api返回码字典
public enum ResponseEnum {

    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    UPLOAD_FAIL( 2, "上传文件失败"),
    USER_NOT_EXIST(3, "用户不存在"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(11, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    ResponseEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }

}
