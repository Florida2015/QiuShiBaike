package cn.edu.nuc.qiushibaike.Interface;

import cn.edu.nuc.qiushibaike.entitys.CunWen;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Flming2015 on 2015/12/29.
 */
public interface QsbkService {
        @GET("article/list/{type}")
        Call<CunWen> getList(@Path("type") String type, @Query("page") int page);
    }

