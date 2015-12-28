package cn.edu.nuc.qiushibaike.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import cn.edu.nuc.qiushibaike.fragments.BlankFragment;

import java.util.List;

/**
 * Created by Flming2015 on 2015/12/28.
 */
public class MyAdapter extends FragmentPagerAdapter {
    private List<String> list;

    public MyAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return BlankFragment.newIntance(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
