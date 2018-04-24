package cn.edu.gdmec.android.boxuegu.Activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.Bean.ExercisesBean;
import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.Utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.adapter.ExercisesDetailListItemAdapter;

public class ActivityExercisesDetailActivity extends Activity {

    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout title_bar;
    private int id;
    private String title;
    private List<ExercisesBean> ebl;
    private ExercisesDetailListItemAdapter adapter;
    private RecyclerView rv_list;
    private TextView tv_dibu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        ebl = new ArrayList<ExercisesBean>();
        initData();
        initView();
    }

    private void initData() {
        try {
            InputStream is = getResources().getAssets().open("chapter" + id + ".xml");
            ebl = AnalysisUtils.getExercisesInfos(is);
            Log.i("ebl", ebl.size() + "");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int count=0;

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_main_title.setText(title);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityExercisesDetailActivity.this.finish();

            }
        });
        adapter = new ExercisesDetailListItemAdapter(ActivityExercisesDetailActivity.this,
                new ExercisesDetailListItemAdapter.OnSelectListener() {
                    @Override
                    public void onSelectA(int position, ImageView iv_a, ImageView iv_b,
                                          ImageView iv_c, ImageView iv_d) {
                        if (ebl.get(position).answer != 1) {
                            ebl.get(position).select = 1;
                        } else {
                            ebl.get(position).select = 0;
                        }
                        switch (ebl.get(position).answer) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);

                                break;
                            case 2:
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                iv_a.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 3:
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                iv_a.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 4:
                                iv_a.setImageResource(R.drawable.exercises_error_icon);
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);

                    }

                    @Override
                    public void onSelectB(int position, ImageView iv_a, ImageView iv_b,
                                          ImageView iv_c, ImageView iv_d) {
                        if (ebl.get(position).answer != 2) {
                            ebl.get(position).select = 2;
                        } else {
                            ebl.get(position).select = 0;
                        }
                        switch (ebl.get(position).answer) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                iv_b.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 2:
                                iv_b.setImageResource(R.drawable.exercises_right_icon);

                                break;
                            case 3:
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                iv_b.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 4:
                                iv_b.setImageResource(R.drawable.exercises_error_icon);
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }

                    @Override
                    public void onSelectC(int position, ImageView iv_a, ImageView iv_b,
                                          ImageView iv_c, ImageView iv_d) {
                        if (ebl.get(position).answer != 3) {
                            ebl.get(position).select = 3;
                        } else {
                            ebl.get(position).select = 0;
                        }
                        switch (ebl.get(position).answer) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                iv_c.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 2:
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                iv_c.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 3:
                                iv_c.setImageResource(R.drawable.exercises_right_icon);

                                break;
                            case 4:
                                iv_c.setImageResource(R.drawable.exercises_error_icon);
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }

                    @Override
                    public void onSelectD(int position, ImageView iv_a, ImageView iv_b,
                                          ImageView iv_c, ImageView iv_d) {
                        if (ebl.get(position).answer != 4) {
                            ebl.get(position).select = 4;
                        } else {
                            ebl.get(position).select = 0;
                        }
                        switch (ebl.get(position).answer) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                iv_d.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 2:
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                iv_d.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 3:
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                iv_d.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 4:
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }
                });
        tv_dibu = (TextView) findViewById(R.id.tv_dibu);
        adapter.setOnItemListener(new ExercisesDetailListItemAdapter.OnItemListener() {
            @Override
            public void onItem(View view, int position) {
                count++;
                tv_dibu.setText("第"+(position+1)+"题完成，共"+adapter.getItemCount()+"题");
                if (count==5){
                    AnalysisUtils.saveExercises(ActivityExercisesDetailActivity.this,id);
                    setResult(RESULT_OK);
                }
            }
        });



        adapter.setData(ebl);

        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        rv_list.setHorizontalScrollBarEnabled(true);
        rv_list.setAdapter(adapter);


    }

}
