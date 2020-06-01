package site.marqstree.mung.test;

import site.marqstree.mung.net.observer.RxObserver;
import site.marqstree.mung.net.request.RxRequestBuilder;
import site.marqstree.mung.test.dataobject.TokenBean;

public class TestSendWxMeesage {

    public static void main(String[] args) {
        RxRequestBuilder.config("https://api.weixin.qq.com");

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
                        String sendUrl = "";
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                });


    }
}
