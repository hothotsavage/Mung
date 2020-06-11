package site.marqstree.mung.rxservice;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.*;

public interface RxServiceByJson {
    @POST
    Observable<String> post(@Url String url, @Body RequestBody body);

    @PUT
    Observable<String> put(@Url String url, @Body RequestBody body);

    @DELETE
    Observable<String> delete(@Url String url, @Body RequestBody body);
}
