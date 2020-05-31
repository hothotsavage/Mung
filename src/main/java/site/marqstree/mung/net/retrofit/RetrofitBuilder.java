package site.marqstree.mung.net.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import site.marqstree.mung.net.config.NetConfig;
import site.marqstree.mung.net.request.RxRequestBuilder;

public class RetrofitBuilder {

    //全局单例retrofit客户端
    public Retrofit retrofitClient;

    //采用静态内部类方法单例化配置对象的建造者实例
    //静态内部类只会被实例化一次，
    //也就是说不管有多少线程，大家拿到的是同一个实例，不会再去进行多次实例化，从而达到了线程安全的目的
    private static class Holder {
        private static final RetrofitBuilder INSTANCE = new RetrofitBuilder(RxRequestBuilder.host);
    }

    //获取单例
    public static RetrofitBuilder getInstance(){
        return Holder.INSTANCE;
    }

    //私有构造方法
    private RetrofitBuilder(String host){
        retrofitClient = new Retrofit.Builder()
                .baseUrl(host)
                .client(initOkHttpClient())
                //.addConverterFactory(GsonConverterFactory.create())
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /*
     * OKHttp创建
     */
    private OkHttpClient initOkHttpClient() {
        //全局OkHttpClient
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        return okHttpBuilder.build();
    }
}
