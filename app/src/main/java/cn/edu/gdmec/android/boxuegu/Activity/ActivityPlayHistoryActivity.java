package cn.edu.gdmec.android.boxuegu.Activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.Bean.VideoBean;
import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.Utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.Utils.DBUtils;
import cn.edu.gdmec.android.boxuegu.adapter.PlayHistoryListItemAdapter;

public class ActivityPlayHistoryActivity extends Activity {

    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_save;
    private RelativeLayout title_bar;
    private ListView lv_list;
    private TextView tv_none;
    private List<VideoBean> vbl;
    private DBUtils db;
    private PlayHistoryListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_history);
        db = DBUtils.getInstance(this);
        vbl = new ArrayList<VideoBean>();
        vbl = db.getVideoHistory(AnalysisUtils.readLoginUserName(this));
        initView();

    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_save = (TextView) findViewById(R.id.tv_save);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        lv_list = (ListView) findViewById(R.id.lv_list);
        tv_none = (TextView) findViewById(R.id.tv_none);
        tv_main_title.setText("播放记录");
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        if (vbl.size() == 0) {
            tv_none.setVisibility(View.VISIBLE);
        }
        adapter = new PlayHistoryListItemAdapter(this);
        adapter.setData(vbl);
        lv_list.setAdapter(adapter);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityPlayHistoryActivity.this.finish();
            }
        });
    }
}
