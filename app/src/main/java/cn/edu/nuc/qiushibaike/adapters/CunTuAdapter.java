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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Flming2015 on 2015/12/29.
 */
public class CunTuAdapter extends BaseAdapter {
    private Context context;
    private List<CunTu.ItemsEntity> list;

    public CunTuAdapter(Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.cuntu_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }

        CunTu.ItemsEntity item = list.get(position);
        ViewHolder holder = (ViewHolder)convertView.getTag();

        if (item.getUser()!=null){
            holder.name.setText(item.getUser().getLogin());

            Picasso.with(context).load(getIconURL(item.getUser().getId(), item.getUser().getIcon()))
                    .transform(new CircleTransformation())
                    .into(holder.icon);
        } else {
            holder.name.setText("匿名用户");
            holder.icon.setImageResource(R.mipmap.ic_launcher);
        }

        holder.content.setText(item.getContent());
        if (item.getImage() == null) {
            holder.image.setVisibility(View.GONE);
        } else {
            holder.image.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(getImageURL(item.getImage()))
                    .resize(parent.getWidth(), 0)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.image);
        }
        //  holder.comments.setText(item.getComments_count());
        //holder.up.setText(item.getVotes().getUp());

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
    public void addAll(Collection<? extends CunTu.ItemsEntity> collection){
        list.addAll(collection);
        notifyDataSetChanged();
    }
    private static class ViewHolder{
        private ImageView icon;
        private ImageView image;
        private TextView content;
        private TextView name;
        private TextView up;
        private TextView comments;

        public ViewHolder(View itemView) {
            icon = ((ImageView) itemView.findViewById(R.id.cuntu_user_icon));
            image = ((ImageView) itemView.findViewById(R.id.cuntu_user_image));
            content = ((TextView) itemView.findViewById(R.id.cuntu_user_content));
            name = ((TextView) itemView.findViewById(R.id.cuntu_user_name));
            up = ((TextView) itemView.findViewById(R.id.cuntu_user_up));
            comments = ((TextView) itemView.findViewById(R.id.cuntu_comments_count));
        }
    }
}
