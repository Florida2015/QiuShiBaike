package cn.edu.nuc.qiushibaike.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.edu.nuc.qiushibaike.R;
import cn.edu.nuc.qiushibaike.Utils.CircleTransformation;
import cn.edu.nuc.qiushibaike.entitys.CunTu;
import cn.edu.nuc.qiushibaike.entitys.CunTuComment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Flming2015 on 2015/12/30.
 */
public class CunTuCommentAdapter extends BaseAdapter{
    private List<CunTuComment.ItemsEntity> list;
    private Context context;

    public CunTuCommentAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cuntu_comments_item,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        CunTuComment.ItemsEntity itemsEntity = list.get(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (itemsEntity.getUser() != null) {
            //获取评论用户头像
            Picasso.with(context)
                    .load(getIconURL(itemsEntity.getId(),itemsEntity.getUser().getIcon()))
                    .transform(new CircleTransformation())
                    .into(holder.userIcon);
            //获取用户名
            holder.userName.setText(itemsEntity.getUser().getLogin());
        }else{
            holder.userIcon.setImageResource(R.mipmap.ic_launcher);
            holder.userName.setText("匿名用户");
        }
        holder.huifuCount.setText(itemsEntity.getFloor());
        holder.commentContent.setText(itemsEntity.getContent());
        holder.commentTime.setText(itemsEntity.getUser().getLast_visited_at());
        return convertView;
    }
    public static String getImageURL(String image){
        String url = "http://pic.qiushibaike.com/system/pictures/%s/%s/%s/%s";
        Pattern pattern = Pattern.compile("(\\d+)\\d{4}");
        Matcher matcher = pattern.matcher(image);
        matcher.find();
        // Log.d(TAG, "getImageURL: " + matcher.group());
        return String.format(url, matcher.group(1), matcher.group(), "medium", image);
    }
    public static String getIconURL(long id, String icon){
        String url = "http://pic.qiushibaike.com/system/avtnew/%s/%s/thumb/%s";
        return String.format(url, id / 10000, id, icon);

    }
    public void addAll(Collection<? extends CunTuComment.ItemsEntity> collection){
        list.addAll(collection);
        notifyDataSetChanged();
    }

    private static class ViewHolder{
        private ImageView userIcon;
        private TextView userName;
        private TextView huifuCount;
        private TextView commentContent;
        private TextView commentTime;
        private TextView zanCount;

        public ViewHolder(View view){

            userIcon = (ImageView) view.findViewById(R.id.cuntu_comment_user_icon);
            userName = (TextView) view.findViewById(R.id.cuntu_comment_user_name);
            huifuCount = (TextView) view.findViewById(R.id.cuntu_comment_user_huifu);
            commentContent = (TextView) view.findViewById(R.id.cuntu_comment_content);
            commentTime = (TextView) view.findViewById(R.id.cuntu_comment_user_time);
            zanCount = (TextView) view.findViewById(R.id.cuntu_comment_user_zan_count);

        }
    }
}
