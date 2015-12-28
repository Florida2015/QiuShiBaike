package cn.edu.nuc.qiushibaike.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import cn.edu.nuc.qiushibaike.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    public static final String TEXT = "text";

    public BlankFragment() {
        // Required empty public constructor
    }
    public static BlankFragment newIntance(String text){
        BlankFragment ret = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(TEXT, text);
        ret.setArguments(args);
        return ret;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_blank2, container, false);
        return ret;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView text = (TextView) view.findViewById(R.id.fragment_text);
        String str = getArguments().getString(TEXT);
        text.setText(str);
    }
}
