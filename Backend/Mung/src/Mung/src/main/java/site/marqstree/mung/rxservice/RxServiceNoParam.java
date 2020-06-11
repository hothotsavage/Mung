package site.marqstree.mung.rxservice;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RxServiceNoParam {
    @GET
    Observable<String> get(@Url String url);
}
