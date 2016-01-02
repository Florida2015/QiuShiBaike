package cn.edu.nuc.qiushibaike.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import cn.edu.nuc.qiushibaike.CunTuCommentsActivity;
import cn.edu.nuc.qiushibaike.Interface.QsbkService;
import cn.edu.nuc.qiushibaike.R;
import cn.edu.nuc.qiushibaike.adapters.CunTuAdapter;
import cn.edu.nuc.qiushibaike.adapters.CunWenAdapter;
import cn.edu.nuc.qiushibaike.entitys.CunTu;
import retrofit.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flming2015 on 2015/12/28.
 */
public class CunTuFragment extends Fragment implements Callback<CunTu>, AdapterView.OnItemClickListener {

    private CunTuAdapter adapter;
    private Call<CunTu> call;
    private ListView listView;

    private List<CunTu.ItemsEntity> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_qiushi_cuntu, container, false);
        listView = (ListView)ret.findViewById(R.id.cuntu_list);

        adapter = new CunTuAdapter(getContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        Retrofit build
                = new Retrofit.Builder().baseUrl("http://m2.qiushibaike.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QsbkService service = build.create(QsbkService.class);
        call = service.getList("image",6);
        call.enqueue(this);
        return ret;
    }

    @Override
    public void onResponse(Response<CunTu> response, Retrofit retrofit) {
        //！！！返回的数据源，这里是网络请求返回的数据
        list = response.body().getItems();
        adapter.addAll(list);
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Toast.makeText(getContext(),"网络错误",Toast.LENGTH_SHORT).show();
    }

    /**
     * 点击ListView 监听事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      //  Object item = parent.getAdapter().getItem(position);
        CunTu.ItemsEntity itemsEntity =list.get(position);
        int userId = itemsEntity.getId();
        if (itemsEntity.getComments_count()!=0) {

            Log.d("CunTuFragment", "评论数为："+itemsEntity.getComments_count());
            Intent intent = new Intent(getContext(), CunTuCommentsActivity.class);
            intent.putExtra("userId", userId);
            intent.putExtra("itemsEntity",itemsEntity);
            //intent.putExtra("data", (Serializable) list);
            startActivity(intent);

        }else{
            Toast.makeText(getContext(),"没有评论",Toast.LENGTH_SHORT).show();
        }
    }
}
