package cn.edu.nuc.qiushibaike.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.edu.nuc.qiushibaike.R;

/**
 * Created by Flming2015 on 2015/12/28.
 */
public class MineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_mine,container,false);
        return ret;
    }
}
