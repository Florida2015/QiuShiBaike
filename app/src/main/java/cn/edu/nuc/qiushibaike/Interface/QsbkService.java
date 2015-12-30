package cn.edu.nuc.qiushibaike.Interface;

import cn.edu.nuc.qiushibaike.entitys.CunTu;
import cn.edu.nuc.qiushibaike.entitys.CunTuComment;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Flming2015 on 2015/12/29.
 */
public interface QsbkService {
        @GET("article/list/{type}")
        Call<CunTu> getList(@Path("type") String type, @Query("page") int page);
        @GET("article/{type}/comments")
        Call<CunTuComment> getCommentList(@Path("type") int type,@Query("page") int page);
    }

