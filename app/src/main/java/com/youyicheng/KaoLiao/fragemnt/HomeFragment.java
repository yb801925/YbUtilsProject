package com.youyicheng.KaoLiao.fragemnt;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyicheng.KaoLiao.listener.HomeListener;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.HomePagerAdapter;
import com.youyicheng.KaoLiao.base.BaseFragment;
import com.youyicheng.KaoLiao.ui.MsgActivity;
import com.youyicheng.KaoLiao.ui.SearshActivity;
import com.youyicheng.KaoLiao.util.DialogUtils;
import com.youyicheng.KaoLiao.util.Logs;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements HomeListener {
    @BindView(R.id.home_sort)
    ImageView homeSort;
    @BindView(R.id.home_msg)
    ImageView homeMsg;
    @BindView(R.id.home_searsh)
    RelativeLayout homeSearsh;
    @BindView(R.id.home_jyt__line)
    View home_jyt__line;
    @BindView(R.id.home_1v1__line)
    View home_1v1__line;
    @BindView(R.id.home_data__line)
    View home_data__line;
    @BindView(R.id.home_jyt_tv)
    TextView home_jyt_tv;
    @BindView(R.id.home_1v1_tv)
    TextView home_1v1_tv;
    @BindView(R.id.home_data_tv)
    TextView home_data_tv;

    @BindView(R.id.home_jyt_ll)
    LinearLayout home_jyt_ll;
    @BindView(R.id.home_1v1_ll)
    LinearLayout home_1v1_ll;
    @BindView(R.id.home_data_ll)
    LinearLayout home_data_ll;

    @BindView(R.id.home_pager)
    ViewPager homePager;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ExperienceFragment experienceFragment;
    private ConsultationFragment consultationFragment;
    private DataFragment dataFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_layout;
    }


    @Override
    protected void initView() {

        experienceFragment = new ExperienceFragment();
        consultationFragment = new ConsultationFragment();
        dataFragment = new DataFragment();

        fragments.add(experienceFragment);
        titles.add("经验帖");
        titles.add("1v1咨询");
        titles.add("备考资料");
        fragments.add(consultationFragment);
        fragments.add(dataFragment);

        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(), fragments, titles);

        homePager.setAdapter(homePagerAdapter);

        homePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    setColor1();
                } else if (i == 1) {
                    setColor2();
                } else {
                    setColor3();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }


    @Override
    protected void initData() {

    }

    private int count = 0;

    @OnClick({R.id.home_sort, R.id.home_msg, R.id.home_searsh, R.id.home_jyt_ll, R.id.home_1v1_ll, R.id.home_data_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_sort:
                DialogUtils.showDialog(getActivity(), homeSort, this);

                break;
            case R.id.home_msg:
                startActivity(new Intent(getActivity(), MsgActivity.class));
                break;
            case R.id.home_searsh:
                Intent intent = new Intent(getActivity(), SearshActivity.class);
                intent.putExtra("state", count);
                startActivity(intent);
                break;

            case R.id.home_jyt_ll:
                count = 0;
                homePager.setCurrentItem(count);
                setColor1();
                break;
            case R.id.home_1v1_ll:
                count = 1;
                homePager.setCurrentItem(count);
                setColor2();
                break;
            case R.id.home_data_ll:
                count = 2;
                homePager.setCurrentItem(count);
                setColor3();
                break;
        }
    }


    @Override
    public void remen(int state) {
        Logs.s("remen");
        if (count == 0) {
            experienceFragment.newData(0);
        } else if (count == 1) {
            consultationFragment.newData(0);
        } else {
            dataFragment.newData(0);
        }
    }

    @Override
    public void newData(int state) {
        Logs.s("newData");
        if (count == 0) {
            experienceFragment.newData(1);
        } else if (count == 1) {
            consultationFragment.newData(1);
        } else {
            dataFragment.newData(1);
        }
    }


    private void setColor3() {
        home_jyt__line.setVisibility(View.INVISIBLE);
        home_1v1__line.setVisibility(View.INVISIBLE);
        home_data__line.setVisibility(View.VISIBLE);
        home_jyt_tv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        home_1v1_tv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        home_data_tv.setTextColor(getActivity().getResources().getColor(R.color.gray_333));

        TextPaint tp1 = home_data_tv.getPaint();
        tp1.setFakeBoldText(true);
        TextPaint tp2 = home_jyt_tv.getPaint();
        tp2.setFakeBoldText(false);
        TextPaint tp3 = home_1v1_tv.getPaint();
        tp3.setFakeBoldText(false);

    }

    private void setColor2() {
        home_jyt__line.setVisibility(View.INVISIBLE);
        home_1v1__line.setVisibility(View.VISIBLE);
        home_data__line.setVisibility(View.INVISIBLE);
        home_jyt_tv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        home_1v1_tv.setTextColor(getActivity().getResources().getColor(R.color.gray_333));
        home_data_tv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));

        TextPaint tp1 = home_data_tv.getPaint();
        tp1.setFakeBoldText(false);
        TextPaint tp2 = home_jyt_tv.getPaint();
        tp2.setFakeBoldText(false);
        TextPaint tp3 = home_1v1_tv.getPaint();
        tp3.setFakeBoldText(true);
    }

    private void setColor1() {
        home_jyt__line.setVisibility(View.VISIBLE);
        home_data__line.setVisibility(View.INVISIBLE);
        home_1v1__line.setVisibility(View.INVISIBLE);
        home_jyt_tv.setTextColor(getActivity().getResources().getColor(R.color.gray_333));
        home_1v1_tv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        home_data_tv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        TextPaint tp1 = home_data_tv.getPaint();
        tp1.setFakeBoldText(false);
        TextPaint tp2 = home_jyt_tv.getPaint();
        tp2.setFakeBoldText(true);
        TextPaint tp3 = home_1v1_tv.getPaint();
        tp3.setFakeBoldText(false);
    }


}
