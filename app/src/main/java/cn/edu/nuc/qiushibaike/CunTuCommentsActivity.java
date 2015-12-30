package cn.edu.nuc.qiushibaike;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import cn.edu.nuc.qiushibaike.Interface.QsbkService;
import cn.edu.nuc.qiushibaike.adapters.CunTuCommentAdapter;
import cn.edu.nuc.qiushibaike.entitys.CunTuComment;
import retrofit.*;

import java.net.URL;

public class CunTuCommentsActivity extends AppCompatActivity implements Callback<CunTuComment> {

    private ListView commentsListView;
    private CunTuCommentAdapter adapter;
    private Call<CunTuComment> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cun_tu_comments);
        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", 0);
        Log.d("CunTuCommentsActivity", "userId = "+userId);

        commentsListView = (ListView) findViewById(R.id.CunTu_comments_listView);
        adapter = new CunTuCommentAdapter(this);
        commentsListView.setAdapter(adapter);
        // String url = "http://m2.qiushibaike.com/article/"+Integer.toString(userId)+"/comments?page=2";
        Retrofit build = new Retrofit.Builder().baseUrl("http://m2.qiushibaike.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QsbkService service = build.create(QsbkService.class);

        call = service.getCommentList(userId,1);
        call.enqueue(this);//回调实现两个方法onResponse

    }

    @Override
    public void onResponse(Response<CunTuComment> response, Retrofit retrofit) {
       adapter.addAll(response.body().getItems());
        Log.d("CunTuCommentsActivity", "评论的数据 = " + response.body().getItems());

    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Toast.makeText(this,"网络错误，稍后重试",Toast.LENGTH_SHORT).show();

    }


}
