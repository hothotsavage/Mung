package site.marqstree.mung.net.convert;

import com.alibaba.fastjson.JSONObject;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import site.marqstree.mung.enums.ResponseEnum;
import site.marqstree.mung.net.bean.StringRespBean;

//将json转换为指定对象
public class RxFuncJson2Bean<T> implements Function<String, Observable<T>> {

    private Class<T> beanClass;

    public RxFuncJson2Bean(Class<T> clazz){
        beanClass = clazz;
    }

    @Override
    public Observable<T> apply(String json) throws Exception {
        //先解析data字符串
        StringRespBean stringRespBean=null;
        try {
            stringRespBean = JSONObject.parseObject(json, StringRespBean.class);
        }catch (Exception e){
            return Observable.error(new Exception(ResponseEnum.ERROR.getDesc()));
        }

        if(stringRespBean==null){
            return Observable.error(new Exception(ResponseEnum.ERROR.getDesc()));
        }

        if(stringRespBean.getCode()!=ResponseEnum.SUCCESS.getCode()){
            return Observable.error(new Exception(stringRespBean.getMessage()));
        }

        String jsonData = stringRespBean.getData();

        if(jsonData==null){
            return Observable.error(new Exception(ResponseEnum.ERROR.getDesc()));
        }

        jsonData = jsonData.trim();
        if(jsonData.isEmpty()){
            return Observable.error(new Exception(ResponseEnum.ERROR.getDesc()));
        }

        //目标类型为字符串，则直接返回
        if(beanClass == String.class){
            return Observable.just((T)jsonData);
        }

        //采用fastjson转成目标类型
        T retBean=null;
        try{
            retBean = JSONObject.parseObject(jsonData, beanClass);
        }catch (Exception e){
            return Observable.error(new Exception("解析json失败"));
        }

        if(retBean==null){
            return Observable.error(new Exception("解析json失败"));
        }

        return Observable.just(retBean);
    }
}
