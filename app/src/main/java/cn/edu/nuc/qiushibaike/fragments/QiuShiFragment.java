package cn.edu.nuc.qiushibaike.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.edu.nuc.qiushibaike.R;
import cn.edu.nuc.qiushibaike.adapters.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flming2015 on 2015/12/28.
 */
public class QiuShiFragment extends Fragment{
    private List<Fragment> fragments;
    private ViewPager  qiushiPager;
    private List<String> titles;
    private TabLayout qiushiTab;

    public QiuShiFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_qiushi,container,false);
        qiushiPager = (ViewPager) ret.findViewById(R.id.qiushi_pager);
        qiushiTab = (TabLayout) ret.findViewById(R.id.qiushi_tab);

        fragments = new ArrayList<Fragment>();

        ZhuanXiangFragment ZXFragment = new ZhuanXiangFragment();
        CunWenFragment CWFragment = new CunWenFragment();
        CunTuFragment CTFragment = new CunTuFragment();
        VideoFragment VFragment = new VideoFragment();
        JingHuaFragment JHFragment = new JingHuaFragment();

        fragments.add(ZXFragment);
        fragments.add(CWFragment);
        fragments.add(CTFragment);
        fragments.add(VFragment);
        fragments.add(JHFragment);

        titles = new ArrayList<String>();
        titles.add("专属");
        titles.add("视频");
        titles.add("纯文");
        titles.add("图文");
        titles.add("精华");

        CommonAdapter adapter
                = new CommonAdapter(getChildFragmentManager(),fragments,titles);
        qiushiPager.setAdapter(adapter);
        qiushiTab.setupWithViewPager(qiushiPager);

       return  ret;
    }
}
