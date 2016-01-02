package cn.edu.nuc.qiushibaike;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import cn.edu.nuc.qiushibaike.Interface.QsbkService;
import cn.edu.nuc.qiushibaike.Utils.CircleTransformation;
import cn.edu.nuc.qiushibaike.adapters.CunTuAdapter;
import cn.edu.nuc.qiushibaike.adapters.CunTuCommentAdapter;
import cn.edu.nuc.qiushibaike.entitys.CunTu;
import cn.edu.nuc.qiushibaike.entitys.CunTuComment;
import com.squareup.picasso.Picasso;
import retrofit.*;

import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CunTuCommentsActivity extends AppCompatActivity implements Callback<CunTuComment>, RadioGroup.OnCheckedChangeListener {
  //  private List<CunTu.ItemsEntity> data;
    private ListView commentsListView;
    private CunTuCommentAdapter adapter;
    private Call<CunTuComment> call;
    private CunTu.ItemsEntity item;
    private CunTuAdapter headAdapter;
    private LayoutInflater inflater;

    private TextView name;
    private ImageView icon;
    private ImageView image;
    private TextView content;
    private TextView up;//好笑
    private TextView comments;//评论数
    private TextView shareCount;//分享数
    private RadioGroup radioGroup;
    private RadioButton supportButton;
    private RadioButton unsupportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cun_tu_comments);
        //actionBar增加返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        commentsListView = (ListView) findViewById(R.id.CunTu_comments_listView);
        //将布局添加到ListView 的头布局，防止此布局重复出现，当然可以同时使用多次addHeaderView
        inflater = LayoutInflater.from(this);
//        View view = null;
//        headAdapter = new CunTuAdapter(this);
//
//        data = (List<CunTu.ItemsEntity>) (getIntent().getSerializableExtra("data"));
//
//        headAdapter.setList(data);

//        position =getIntent().getIntExtra("position",0);
//        view = headAdapter.getView(position,view,commentsListView);

        View itemView = inflater.inflate(R.layout.cuntu_item,commentsListView,false);
        name = (TextView) itemView.findViewById(R.id.cuntu_user_name);
        icon = ((ImageView) itemView.findViewById(R.id.cuntu_user_icon));
        image = ((ImageView) itemView.findViewById(R.id.cuntu_user_image));
        content = ((TextView) itemView.findViewById(R.id.cuntu_user_content));
        up = ((TextView) itemView.findViewById(R.id.cuntu_user_up));
        comments = ((TextView) itemView.findViewById(R.id.cuntu_comments_count));
        shareCount = (TextView) itemView.findViewById(R.id.cuntu_content_share_count);
        supportButton = (RadioButton) itemView.findViewById(R.id.cuntu_operation_support);
        unsupportButton = (RadioButton) itemView.findViewById(R.id.cuntu_operation_unsupport);
        radioGroup = (RadioGroup) itemView.findViewById(R.id.operation_radiogroup);
        radioGroup.setOnCheckedChangeListener(this);

        item = (CunTu.ItemsEntity) (getIntent().getSerializableExtra("itemsEntity"));


        if (item.getUser()!=null){
            name.setText(item.getUser().getLogin());

            Picasso.with(this).load(getIconURL(item.getUser().getId(), item.getUser().getIcon()))
                    .transform(new CircleTransformation())
                    .into(icon);
        } else {
            name.setText("匿名用户");
            icon.setImageResource(R.mipmap.ic_launcher);
        }

        content.setText(item.getContent());
        if (item.getImage() == null) {
           image.setVisibility(View.GONE);
        } else {
            image.setVisibility(View.VISIBLE);
            Picasso.with(this)
                    .load(getImageURL(item.getImage()))
                    .resize(800,300)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(image);
        }

        //！！！！没有toString,直接放数据导致出错
        comments.setText(Integer.toString(item.getComments_count()));
        up.setText(Integer.toString(item.getVotes().getUp()));
        shareCount.setText(Integer.toString(item.getShare_count()));

        commentsListView.addHeaderView(itemView);
        // commentsListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.cuntu_item, commentsListView,false));



        int userId = getIntent().getIntExtra("userId", 0);
        Log.d("CunTuCommentsActivity", "userId haha = "+userId);


        adapter = new CunTuCommentAdapter(this);
        commentsListView.setAdapter(adapter);


        Retrofit build
                = new Retrofit.Builder().baseUrl("http://m2.qiushibaike.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QsbkService service = build.create(QsbkService.class);

        call = service.getCommentList(userId,1);
        call.enqueue(this);//回调实现两个方法onResponse、onFailure

    }

    @Override
    public void onResponse(Response<CunTuComment> response, Retrofit retrofit) {
        Log.d("CunTuCommentsActivity", "评论的数据 = " + response.body().getItems());
        adapter.addAll(response.body().getItems());

    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Toast.makeText(this,"网络错误，稍后重试",Toast.LENGTH_SHORT).show();

    }




    public static String getImageURL(String image){
        String url = "http://pic.qiushibaike.com/system/pictures/%s/%s/%s/%s";
        Pattern pattern = Pattern.compile("(\\d+)\\d{4}");
        Matcher matcher = pattern.matcher(image);
        matcher.find();
        // Log.d(TAG, "getImageURL: " + matcher.group());
        return String.format(url, matcher.group(1), matcher.group(), "medium", image);
    }
    public static String getIconURL(long id, String icon){
        String url = "http://pic.qiushibaike.com/system/avtnew/%s/%s/thumb/%s";
        return String.format(url, id / 10000, id, icon);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.cuntu_operation_support:
                //TODO:点击“好笑”实现数据更新
                break;
            case R.id.cuntu_operation_unsupport:
                //TODO:点击“不好笑”实现数据更新
                break;
        }
        //点击“好笑”或“不好笑”，图片会有变小和放大的效果
        View buttonView = radioGroup.findViewById(checkedId);
        if (buttonView != null) {
            ViewCompat.setScaleX(buttonView, 0.8f);
            ViewCompat.setScaleY(buttonView, 0.8f);
            ViewCompat.animate(buttonView).scaleX(1).scaleY(1).setDuration(500).start();
        }
    }
}
