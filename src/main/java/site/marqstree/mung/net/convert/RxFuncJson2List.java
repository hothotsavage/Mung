package site.marqstree.mung.net.convert;

import com.alibaba.fastjson.JSONObject;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import site.marqstree.mung.enums.ResponseEnum;
import site.marqstree.mung.net.bean.StringRespBean;

import java.util.List;

public class RxFuncJson2List<T> implements Function<String, Observable<List<T>>> {
    private Class<T> beanClass;

    public RxFuncJson2List(Class<T> clazz){
        beanClass = clazz;
    }

    @Override
    public Observable<List<T>> apply(String json) throws Exception {
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

        //采用fastjson转成目标类型
        List<T> retBeanList=null;
        try{
            retBeanList = JSONObject.parseArray(jsonData, beanClass);
        }catch (Exception e){
            return Observable.error(new Exception("解析json失败"));
        }

        if(retBeanList==null){
            return Observable.error(new Exception("解析json失败"));
        }

        return Observable.just(retBeanList);
    }
}
