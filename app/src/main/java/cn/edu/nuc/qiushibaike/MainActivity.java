package cn.edu.nuc.qiushibaike;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import cn.edu.nuc.qiushibaike.adapters.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigation;
    private ActionBarDrawerToggle toggle;
    private TabLayout tab;

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

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        List<String> list = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            list.add(String.format("第"+"%d"+"项",i));
        }
        pager.setAdapter(new MyAdapter(getSupportFragmentManager(),list));

        tab = (TabLayout) findViewById(R.id.tab_layout);
        tab.setupWithViewPager(pager);

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
        switch (itemId){
            case R.id.item_qiushi:
                break;
            case R.id.item_qiushicircle:
                break;
            case R.id.item_nearby:
                break;
            case R.id.item_message:
                break;
            case R.id.item_mine:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
