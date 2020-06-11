package site.marqstree.mung.net.convert;

import com.alibaba.fastjson.JSONObject;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import site.marqstree.mung.enums.ResponseEnum;
import site.marqstree.mung.net.bean.BaseRespBean;
import site.marqstree.mung.net.exception.NetException;

public class RxFuncJson2Boolean implements Function<String, Observable<Boolean>> {
    @Override
    public Observable<Boolean> apply(String json) throws Exception {
        BaseRespBean retData;
        try {
            retData = JSONObject.parseObject(json, BaseRespBean.class);
        }catch (Exception e){
            retData = new BaseRespBean(ResponseEnum.ERROR.getCode(),ResponseEnum.ERROR.getDesc());
        }
        //返回失败码，不管转换json是否成功，都走onError方法
        if(retData.getCode()!= ResponseEnum.SUCCESS.getCode()){
            return Observable.error(
                    new Exception(retData.getMessage())
            );
        }

        //网络响应码为成功时，
        // 返回将Observable<String>转为Observable<Boolean>
        return Observable.just(true);
    }
}
