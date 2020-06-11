package site.marqstree.mung.rxservice;

import io.reactivex.Observable;
import retrofit2.http.*;
import java.util.WeakHashMap;

public interface RxServiceByParam {
    @GET
    Observable<String> get(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap WeakHashMap<String, Object> params);

    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap WeakHashMap<String, Object> params);

    @DELETE
    Observable<String> delete(@Url String url, @QueryMap WeakHashMap<String, Object> params);

}
