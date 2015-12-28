package cn.edu.nuc.qiushibaike;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import cn.edu.nuc.qiushibaike.adapters.CommonAdapter;
import cn.edu.nuc.qiushibaike.fragments.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigation;
    private ActionBarDrawerToggle toggle;
    private TabLayout tab;
    private ViewPager pager;
    private List<Fragment> fragments;
    private List<String> titles;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigation = (NavigationView) findViewById(R.id.navigation);

        navigation.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toggle = new ActionBarDrawerToggle(this,drawer,0,0);

        toggle.syncState();

        drawer.setDrawerListener(toggle);

        pager = (ViewPager) findViewById(R.id.pager);

        fragments = new ArrayList<>();

        QiuShiFragment QSFragment = new QiuShiFragment();
        QiuYouCircleFragment QYQFragment = new QiuYouCircleFragment();
        NearByFragment NBFragment = new NearByFragment();
        MessageFragment MFragment = new MessageFragment();
        MineFragment MineFragment = new MineFragment();

         fragments.add(QSFragment);
         fragments.add(QYQFragment);
         fragments.add(NBFragment);
         fragments.add(MFragment);
         fragments.add(MineFragment);
//
//        titles = new ArrayList<>();
//        titles.add("糗事");
//        titles.add("糗友圈");
//        titles.add("发现");
//        titles.add("小纸条");
//        titles.add("我");
        fragmentManager = getSupportFragmentManager();
        CommonAdapter adapter = new CommonAdapter(fragmentManager,fragments,null);
        pager.setAdapter(adapter);
       // tab = (TabLayout) findViewById(R.id.tab_layout);
       // tab.setupWithViewPager(pager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        int index = 0;
        switch (itemId){
            case R.id.item_qiushi:
                index = 0;
                break;
            case R.id.item_qiushicircle:
                index = 1;
                break;
            case R.id.item_nearby:
                index = 2;
                break;
            case R.id.item_message:
                index = 3;
                break;
            case R.id.item_mine:
                index = 4;
                break;
        }
        pager.setCurrentItem(index);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
