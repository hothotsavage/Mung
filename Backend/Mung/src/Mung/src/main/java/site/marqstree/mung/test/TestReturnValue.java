package site.marqstree.mung.test;

import site.marqstree.mung.net.observer.RxObserver;
import site.marqstree.mung.net.request.RxRequestBuilder;
import site.marqstree.mung.test.dataobject.TokenBean;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestReturnValue {

    public static void main(String[] args) {
        RxRequestBuilder.config("https://api.weixin.qq.com");

        ////定义耗时Task,5秒之后返回1
        //FutureTask<TokenBean> futureTask = new FutureTask<TokenBean>(
        //        new Callable<TokenBean>() {
        //            @Override
        //            public TokenBean call() throws Exception {
        //
        //                final TokenBean[] retToken = {null};
        //
        //                //获取apn
        //                RxRequestBuilder.build()
        //                        .setUrl("/cgi-bin/token")
        //                        .setParam("grant_type", "client_credential")
        //                        .setParam("appid", "wx94131d07f4097e73")
        //                        .setParam("secret", "36148a8526b6829508504d1bf4b0f425")
        //                        .get()
        //                        .json2RawBean(TokenBean.class)
        //                        .subscribe(new RxObserver<TokenBean>() {
        //                            @Override
        //                            public void onNext(TokenBean tokenBean) {
        //                                System.out.println(tokenBean.toString());
        //                                retToken[0] = tokenBean;
        //                            }
        //
        //                            @Override
        //                            public void onError(Throwable e) {
        //                                System.out.println(e.getMessage());
        //                            }
        //
        //                        });
        //                return retToken[0];
        //            }
        //        });
        //
        //TokenBean tokenBean=null;
        //try {
        //    tokenBean = futureTask.get();
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        //System.out.println(tokenBean.toString());


        final TokenBean[] retToken = {null};

        //获取apn
        RxRequestBuilder.build()
                .setUrl("/cgi-bin/token")
                .setParam("grant_type", "client_credential")
                .setParam("appid", "wx94131d07f4097e73")
                .setParam("secret", "36148a8526b6829508504d1bf4b0f425")
                .get()
                .json2RawBean(TokenBean.class)
                .subscribe(new RxObserver<TokenBean>() {
                    @Override
                    public void onNext(TokenBean tokenBean) {
                        System.out.println(tokenBean.toString());
                        retToken[0] = tokenBean;
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                });
        System.out.println(retToken[0].toString());;

    }
}
