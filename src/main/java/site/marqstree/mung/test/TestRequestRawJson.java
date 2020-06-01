package site.marqstree.mung.test;

import site.marqstree.mung.net.observer.RxObserver;
import site.marqstree.mung.net.request.RxRequestBuilder;
import site.marqstree.mung.test.dataobject.TokenBean;

public class TestRequestRawJson {

    public static void main(String[] args) {
        RxRequestBuilder.config("http://localhost:5050");

        RxRequestBuilder.build()
                .setUrl("/api/push/rawjson")
                .setParam("param1", "value1")
                .setBodyByJson("{'name': 'tom','age': 11}")
                .post()
                .json2RawBean(String.class)
                .subscribe(new RxObserver<String>() {
                    @Override
                    public void onNext(String ret) {
                        System.out.println(ret);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                });


    }
}
