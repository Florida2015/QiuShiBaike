package cn.edu.nuc.qiushibaike.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import cn.edu.nuc.qiushibaike.Interface.QsbkService;
import cn.edu.nuc.qiushibaike.R;
import cn.edu.nuc.qiushibaike.adapters.CunWenAdapter;
import cn.edu.nuc.qiushibaike.entitys.CunWen;
import retrofit.*;

import java.util.List;

/**
 * Created by Flming2015 on 2015/12/28.
 */
public class CunWenFragment extends Fragment implements Callback<CunWen> {

    private CunWenAdapter adapter;
    private Call<CunWen> call;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_qiushi_cunwen, container, false);
        listView = (ListView)ret.findViewById(R.id.cunwen_list);

        adapter = new CunWenAdapter(getContext());
        listView.setAdapter(adapter);

        Retrofit build
                = new Retrofit.Builder().baseUrl("http://m2.qiushibaike.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QsbkService service = build.create(QsbkService.class);
        call = service.getList("image",1);
        call.enqueue(this);
        return ret;
    }

    @Override
    public void onResponse(Response<CunWen> response, Retrofit retrofit) {
        adapter.addAll(response.body().getItems());
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Toast.makeText(getContext(),"网络错误",Toast.LENGTH_SHORT).show();
    }
}
