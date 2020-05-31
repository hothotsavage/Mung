package site.marqstree.mung.test;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import site.marqstree.mung.net.config.NetConfig;
import site.marqstree.mung.net.observer.RxObserver;
import site.marqstree.mung.net.request.RxRequest;
import site.marqstree.mung.net.request.RxRequestBuilder;
import site.marqstree.mung.test.dataobject.SubmitPoint;

import java.util.List;

public class TestNet {

    public static void main(String[] args) {

        RxRequestBuilder.config("http://localhost:5050");

        //RxRequestBuilder.build()
        //        .setUrl("/api/submitpoint/querynear")
        //        .setParam("lat", 30.5455298053976)
        //        .setParam("lng", 114.27747648130438)
        //        .setParam("count", 2)
        //        .get()
        //        .json2List(SubmitPoint.class)
        //        .subscribe(new RxObserver<List<SubmitPoint>>() {
        //            @Override
        //            public void onNext(List<SubmitPoint> submitPoints) {
        //                System.out.println(submitPoints.toString());
        //            }
        //
        //            @Override
        //            public void onError(Throwable e) {
        //                System.out.println(e.getMessage());
        //            }
        //
        //        });


        RxRequestBuilder.build()
                .setUrl("http://localhost:5050/api/submitpoint/getbyid")
                .setParam("id", 83)
                .get()
                .json2Bean(SubmitPoint.class)
                .subscribe(new RxObserver<SubmitPoint>() {
                    @Override
                    public void onNext(SubmitPoint submitPoints) {
                        System.out.println(submitPoints.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                });

    }
}
