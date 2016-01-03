package cn.edu.nuc.qiushibaike.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
public class CunTuFragment extends Fragment implements Callback<CunTu>, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {

    private CunTuAdapter adapter;
    private Call<CunTu> call;
    private ListView listView;
    private List<CunTu.ItemsEntity> list;

    private SwipeRefreshLayout swipeRefreshLayout;
    private int page = 1;
    private QsbkService service;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_qiushi_cuntu, container, false);
        return ret;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView)view.findViewById(R.id.cuntu_list);

        adapter = new CunTuAdapter(getContext());
        listView.setAdapter(adapter);
        //监听ListView 的"点击"事件
        listView.setOnItemClickListener(this);
        //监听listView的状态，分为三种
        listView.setOnScrollListener(this);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.cuntu_swiperefresh);
        //监听SwipeRefreshLayout的刷新
        swipeRefreshLayout.setOnRefreshListener(this);

        Retrofit build
                = new Retrofit.Builder().baseUrl("http://m2.qiushibaike.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = build.create(QsbkService.class);
        call = service.getList("image", page);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Response<CunTu> response, Retrofit retrofit) {
        //！！！返回的数据源，这里是网络请求返回的数据
        if (response==null||response.body()==null){
            return;
        }
        if (page == 1){
            adapter.clear();
            list = response.body().getItems();
            adapter.addAll(list);
        }else{
            list = response.body().getItems();
            adapter.addAll(list);
        }
        //数据刷新完之后，停止刷新
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Toast.makeText(getContext(),"网络错误",Toast.LENGTH_SHORT).show();
        //数据刷新完之后，停止刷新
        swipeRefreshLayout.setRefreshing(false);
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

    @Override
    public void onRefresh() {
        page = 1;
        call = service.getList("image",page);
        call.enqueue(this);
    }


    private boolean loadMore = false;
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case SCROLL_STATE_IDLE:
                if (loadMore){
                    page ++;
                    call = service.getList("image",page);
                    call.enqueue(this);
                }
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem+visibleItemCount>=totalItemCount){
            loadMore = true;
        }else{
            loadMore = false;
        }
    }
}
