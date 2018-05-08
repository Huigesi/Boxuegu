package cn.edu.gdmec.android.boxuegu.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.Bean.VideoBean;
import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.Utils.DBUtils;
import cn.edu.gdmec.android.boxuegu.adapter.VideoListItemAdapter;

public class ActivityVideoListActivity extends Activity {

    private TextView tvIntro;
    private TextView tvVideo;
    private ListView lvVideoList;
    private ScrollView svChapterIntro;
    private TextView tvChapterIntro;
    private List<VideoBean> videoList;
    private int chapterId;
    private String intro;
    private VideoListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        chapterId = getIntent().getIntExtra("id", 0);
        intro = getIntent().getStringExtra("intro");
        initData();
        initView();

    }

    //把json文件转换为字符串
    private String read(InputStream is) {
        BufferedReader reader = null;
        StringBuilder sb = null;
        String line = null;
        try {
            sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(is));

            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            if (is != null) {
                try {
                    is.close();
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    //解析json
    private void initData() {
        JSONArray jsonArray;
        try {
            InputStream is = getResources().getAssets().open("data.json");
            jsonArray = new JSONArray(read(is));
            videoList = new ArrayList<VideoBean>();
            for (int i = 0; i < jsonArray.length(); i++) {
                VideoBean bean = new VideoBean();
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                if (jsonObj.getInt("chapterId") == chapterId) {
                    bean.chapterId = jsonObj.getInt("chapterId");
                    bean.videoId = Integer.parseInt(jsonObj.getString("videoId"));
                    bean.title = jsonObj.getString("title");
                    bean.secondTitle = jsonObj.getString("secondTitle");
                    bean.videoPath = jsonObj.getString("videoPath");
                    videoList.add(bean);
                }
                bean = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




    private void initView() {
        tvIntro = (TextView) findViewById(R.id.tv_intro);
        tvVideo = (TextView) findViewById(R.id.tv_video);
        lvVideoList = (ListView) findViewById(R.id.lv_video_list);
        svChapterIntro = (ScrollView) findViewById(R.id.sv_chapter_intro);
        tvChapterIntro = (TextView) findViewById(R.id.tv_chapter_intro);
        adapter=new VideoListItemAdapter(this);
        lvVideoList.setAdapter(adapter);
        adapter.setData(videoList);
        tvChapterIntro.setText(intro);
        tvIntro.setBackgroundColor(Color.parseColor("#30B4FF"));
        tvVideo.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvIntro.setTextColor(Color.parseColor("#FFFFFF"));
        tvVideo.setTextColor(Color.parseColor("#000000"));
        tvIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvVideoList.setVisibility(View.GONE);
                svChapterIntro.setVisibility(View.VISIBLE);
                tvIntro.setBackgroundColor(Color.parseColor("#30B4FF"));
                tvVideo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tvIntro.setTextColor(Color.parseColor("#FFFFFF"));
                tvVideo.setTextColor(Color.parseColor("#000000"));
            }
        });
        tvVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvVideoList.setVisibility(View.VISIBLE);
                svChapterIntro.setVisibility(View.GONE);
                tvIntro.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tvVideo.setBackgroundColor(Color.parseColor("#30B4FF"));
                tvIntro.setTextColor(Color.parseColor("#000000"));
                tvVideo.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            int position = data.getIntExtra("position", 0);
            adapter.setSelectedPosition(position);
            lvVideoList.setVisibility(View.VISIBLE);
            svChapterIntro.setVisibility(View.GONE);
            tvIntro.setBackgroundColor(Color.parseColor("#FFFFFF"));
            tvVideo.setBackgroundColor(Color.parseColor("#30B4FF"));
            tvIntro.setTextColor(Color.parseColor("#000000"));
            tvVideo.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }
}
