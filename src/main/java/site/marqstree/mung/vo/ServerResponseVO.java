package site.marqstree.mung.vo;

import lombok.Data;
import site.marqstree.mung.enums.ResponseEnum;

@Data
public class ServerResponseVO<T> {

    private int code;
    private String message;
    private T data;

    private ServerResponseVO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ServerResponseVO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private ServerResponseVO(int code) {
        this.code = code;
    }

    public static <T> ServerResponseVO<T> createBySuccess(){
        return new ServerResponseVO<T>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc());
    }
    public static <T> ServerResponseVO<T> createBySuccessMessage(String msg){
        return new ServerResponseVO<T>(ResponseEnum.SUCCESS.getCode(),msg);
    }
    public static <T> ServerResponseVO<T> createBySuccess(T data){
        return new ServerResponseVO<T>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc(), data);
    }
    public static <T> ServerResponseVO<T> createBySuccess(String msg, T data){
        return new ServerResponseVO<T>(ResponseEnum.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponseVO<T> createByError(){
        return new ServerResponseVO<T>(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getDesc());
    }

    public static <T> ServerResponseVO<T> createByErrorMessage(String msg){
        return new ServerResponseVO<T>(ResponseEnum.ERROR.getCode(),msg);
    }
    public static <T> ServerResponseVO<T> createByErrorCodeMessage(int code, String msg){
        return new ServerResponseVO<T>(code,msg);
    }
    public static <T> ServerResponseVO<T> createByErrorEnum(ResponseEnum re){
        return new ServerResponseVO<T>(re.getCode(), re.getDesc());
    }

}
