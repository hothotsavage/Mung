package site.marqstree.mung.net.convert;

import com.alibaba.fastjson.JSONObject;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import site.marqstree.mung.enums.ResponseEnum;
import site.marqstree.mung.net.bean.StringRespBean;

//将json转换为指定对象
public class RxFuncJson2RawBean<T> implements Function<String, Observable<T>> {

    private Class<T> beanClass;

    public RxFuncJson2RawBean(Class<T> clazz){
        beanClass = clazz;
    }

    @Override
    public Observable<T> apply(String json) throws Exception {
        if(json==null){
            return Observable.error(new Exception(ResponseEnum.ERROR.getDesc()));
        }

        json = json.trim();
        if(json.isEmpty()){
            return Observable.error(new Exception(ResponseEnum.ERROR.getDesc()));
        }

        //目标类型为字符串，则直接返回
        if(beanClass == String.class){
            return Observable.just((T)json);
        }

        //采用fastjson转成目标类型
        T retBean=null;
        try{
            retBean = JSONObject.parseObject(json, beanClass);
        }catch (Exception e){
            return Observable.error(new Exception("解析json失败"));
        }

        if(retBean==null){
            return Observable.error(new Exception("解析json失败"));
        }

        return Observable.just(retBean);
    }
}
