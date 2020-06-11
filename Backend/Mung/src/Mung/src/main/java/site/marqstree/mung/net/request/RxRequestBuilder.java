package site.marqstree.mung.net.request;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import site.marqstree.mung.net.convert.RxFuncJson2Bean;
import site.marqstree.mung.net.convert.RxFuncJson2Boolean;
import site.marqstree.mung.net.convert.RxFuncJson2List;
import site.marqstree.mung.net.convert.RxFuncJson2RawBean;
import site.marqstree.mung.net.method.HttpMethod;
import java.io.File;
import java.util.List;
import java.util.WeakHashMap;

public class RxRequestBuilder<T> {

    public static String host;

    public static void config(String baseUrl){
        host = baseUrl;
    }

    public static RxRequestBuilder build(){
        return new RxRequestBuilder();
    }

    private RxRequest rxRequest = new RxRequest();

    public RxRequestBuilder setUrl(String url){
        rxRequest.setUrl(url);
        return this;
    }

    public RxRequestBuilder setParams(WeakHashMap<String, Object> params){
        rxRequest.setParams(params);
        return this;
    }

    public RxRequestBuilder setParam(String key, Object value){
        rxRequest.getParams().put(key,value);
        return this;
    }

    public RxRequestBuilder setFile(File file){
        rxRequest.setFile(file);
        return this;
    }

    public RxRequestBuilder setFile(String filePath){
        rxRequest.setFile(new File(filePath));
        return this;
    }

    public RxRequestBuilder setBodyByJson(String json){
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json);
        rxRequest.setBody(body);
        return this;
    }

    public RxRequestBuilder get(){
        rxRequest.setJsonObservable(rxRequest.request(HttpMethod.GET));
        return this;
    }

    public RxRequestBuilder post(){
        rxRequest.setJsonObservable(rxRequest.request(HttpMethod.POST));
        return this;
    }

    public RxRequestBuilder put(){
        rxRequest.setJsonObservable(rxRequest.request(HttpMethod.PUT));
        return this;
    }

    public RxRequestBuilder delete(){
        rxRequest.setJsonObservable(rxRequest.request(HttpMethod.DELETE));
        return this;
    }

    public RxRequestBuilder upload(){
        rxRequest.setJsonObservable(rxRequest.request(HttpMethod.UPLOAD));
        return this;
    }

    //使用当前线程同步将code转换成boolean
    public Observable<Boolean> json2Boolean(){
        return rxRequest.getJsonObservable()
                .flatMap(new RxFuncJson2Boolean());
    }

    //使用当前线程同步将data数据转换成beanClass
    public Observable<T> json2Bean(Class<T> beanClass){
        return rxRequest.getJsonObservable()
                .flatMap(new RxFuncJson2Bean<T>(beanClass));
    }

    //使用当前线程同步将data数据转换成List
    public Observable<List<T>> json2List(Class<T> beanClass){
        return rxRequest.getJsonObservable()
                .flatMap(new RxFuncJson2List<T>(beanClass));
    }

    //使用当前线程同步转换成beanClass
    public Observable<T> json2RawBean(Class<T> beanClass){
        return rxRequest.getJsonObservable()
                .flatMap(new RxFuncJson2RawBean<T>(beanClass));
    }
}
