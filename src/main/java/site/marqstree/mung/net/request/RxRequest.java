package site.marqstree.mung.net.request;

import io.reactivex.Observable;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import site.marqstree.mung.net.method.HttpMethod;
import site.marqstree.mung.net.retrofit.RetrofitBuilder;
import site.marqstree.mung.rxservice.*;
import java.io.File;
import java.util.WeakHashMap;

//retrofit发起http请求的客户端
@Data
@NoArgsConstructor
public class RxRequest {

    private String url;
    private WeakHashMap<String,Object> params= new WeakHashMap<String,Object>();
    private RequestBody body;
    private File file;
    private Observable<String> jsonObservable;

    //无参get方式RxService实例
    //返回json字符串
    private RxServiceNoParam rxServiceNoParam = RetrofitBuilder.getInstance()
            .retrofitClient.create(RxServiceNoParam.class);


    private RxServiceByParam rxServiceByParam = RetrofitBuilder.getInstance()
            .retrofitClient.create(RxServiceByParam.class);

    //获取Retrofit的json方式传参RxService实例
    //返回json字符串
    private RxServiceByJson rxServiceByJson = RetrofitBuilder.getInstance()
            .retrofitClient.create(RxServiceByJson.class);

    //获取Retrofit的上传RxService实例
    //返回json字符串
    private RxServiceUpload rxServiceUpload = RetrofitBuilder.getInstance()
            .retrofitClient.create(RxServiceUpload.class);

    //获取Retrofit的下载RxService实例
    //返回json字符串
    private RxServiceDownload rxServiceDownload = RetrofitBuilder.getInstance()
            .retrofitClient.create(RxServiceDownload.class);

    public Observable<String> request(HttpMethod method){
        //发起网络请求
        //耗时操作
        switch (method){
            case GET:
                if(params.isEmpty()){
                    return rxServiceNoParam.get(url);
                }
                else {
                    return rxServiceByParam.get(url,params);
                }
            case POST:
                if(params.isEmpty()){
                    return rxServiceByJson.post(url,body);
                }
                else {
                    return rxServiceByParam.post(url,params);
                }
            case PUT:
                if(params.isEmpty()){
                    return rxServiceByJson.put(url,body);
                }
                else {
                    return rxServiceByParam.put(url,params);
                }
            case DELETE:
                if(params.isEmpty()){
                    return rxServiceByJson.delete(url,body);
                }
                else {
                    return rxServiceByParam.delete(url,params);
                }
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), file);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                return rxServiceUpload.upload(url, body);
            default:
                return null;
        }
    }
}
