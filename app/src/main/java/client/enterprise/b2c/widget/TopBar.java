package client.enterprise.b2c.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import client.enterprise.b2c.R;
import client.enterprise.b2c.util.ScreenUtils;

/**
 * Created by raohoulin on 2015.12.26.
 */
public class TopBar extends LinearLayout {
    // 包含topbar上的元素：左按钮、右按钮、标题
    private ImageView mLeftView;
    private ImageView mRightView;
    private TextView mTitleView;

    // 布局属性，用来控制组件元素在ViewGroup中的位置
    private LayoutParams mLeftParams, mTitlepParams, mRightParams;

    // 左按钮的属性值，即我们在atts.xml文件中定义的属性
    private Drawable mLeftImgSrc;
    // 右按钮的属性值，即我们在atts.xml文件中定义的属性
    private Drawable mRightImgSrc;
    // 标题的属性值，即我们在atts.xml文件中定义的属性
    private int mTitleTextColor;
    private String mTitle;
    private Drawable mTitleLeftImg;

    private topbarClickListener mListener;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TopBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(Context context, AttributeSet attrs) {
        setBackgroundResource(R.drawable.top_bar_bg);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mLeftImgSrc = ta.getDrawable(R.styleable.TopBar_leftImgSrc);
        mRightImgSrc = ta.getDrawable(R.styleable.TopBar_rightImgSrc);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_my_titleTextColor, 0);
        mTitleLeftImg = ta.getDrawable(R.styleable.TopBar_my_titleLeftImg);
        mTitle = ta.getString(R.styleable.TopBar_my_title);
        ta.recycle();

        mLeftView = new ImageView(context);
        mRightView = new ImageView(context);
        mTitleView = new TextView(context);

        // 为创建的组件元素赋值
        // 值就来源于我们在引用的xml文件中给对应属性的赋值
        mLeftView.setImageDrawable(mLeftImgSrc);
        mLeftView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        mRightView.setImageDrawable(mRightImgSrc);

        mRightView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(16);
        mTitleView.setClickable(true);
        mTitleLeftImg.setBounds(0, 0, mTitleLeftImg.getMinimumWidth(), mTitleLeftImg.getMinimumHeight());
        mTitleView.setCompoundDrawables(mTitleLeftImg,null,null,null);
        mTitleView.setBackgroundResource(R.drawable.top_bar_text_bg);
        mTitleView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);

        setOrientation(LinearLayout.HORIZONTAL);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        addView(mLeftView, 0, mLeftParams);

        mTitlepParams = new LayoutParams(0,LayoutParams.MATCH_PARENT);
        mTitlepParams.weight = 1;
        mTitlepParams.setMargins(ScreenUtils.dip2px(getContext(), 2), ScreenUtils.dip2px(getContext(), 5), ScreenUtils.dip2px(getContext(), 2), ScreenUtils.dip2px(getContext(), 5));
        addView(mTitleView, 1, mTitlepParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        addView(mRightView, 2, mRightParams);

        mLeftView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });
        mRightView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });
        mTitleView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.titleViewClick();
            }
        });
    }

    public void setOnTopbarClickListener(topbarClickListener mListener) {
        this.mListener = mListener;
    }

    public void setButtonVisable(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftView.setVisibility(View.VISIBLE);
            } else {
                mRightView.setVisibility(View.VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftView.setVisibility(View.GONE);
            } else {
                mRightView.setVisibility(View.GONE);
            }
        }
    }

    public interface topbarClickListener {
        void leftClick();
        void rightClick();
        void titleViewClick();
    }

}
