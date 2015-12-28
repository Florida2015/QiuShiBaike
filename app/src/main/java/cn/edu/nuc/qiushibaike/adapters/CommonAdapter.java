package cn.edu.nuc.qiushibaike.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Flming2015 on 2015/12/28.
 */
public class CommonAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragements;
    private List<String> titles;

    public CommonAdapter(FragmentManager fm, List<Fragment> fragements, List<String> titles) {
        super(fm);
        this.fragements = fragements;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragements.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (fragements != null){
            ret = fragements.size();
        }
        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
