package site.marqstree.mung.rxservice;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

import java.util.WeakHashMap;

public interface RxServiceDownload {
    @Streaming  //@Streaming：表示一边下载，一边写磁盘，此时必须使用异步调用。若不用Streaming，则全部写入内存，可能导致内存溢出
    @GET
    Observable<ResponseBody> download(@Url String url, @QueryMap WeakHashMap<String, Object> params);

}
