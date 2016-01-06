package client.enterprise.b2c.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import client.enterprise.b2c.R;

/**
 * Created by raohoulin on 2015.12.26.
 */
public class TopBarSon extends FrameLayout {
    // 包含topbar上的元素：左按钮、右按钮、标题
    private ImageView oneView;
    private View twoView;
    private ImageView threeView;
    private ImageView foreView;
    private LinearLayout linearLayout;
    private ImageView logoView;

    // 布局属性，用来控制组件元素在ViewGroup中的位置
    private LinearLayout.LayoutParams oneParams, twoParams, threeParams, foreParams;
    private LayoutParams logoParams;

    private Drawable oneImgSrc;
    private Drawable logoImgSrc;
    private Drawable threeImgSrc;
    private Drawable foreImgSrc;
    private int isVisibility;

    private topbarClickListener mListener;

    public TopBarSon(Context context) {
        super(context);
    }

    public TopBarSon(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TopBarSon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TopBarSon(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(Context context, AttributeSet attrs) {
        setBackgroundResource(R.drawable.top_bar_bg);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBarSon);
        oneImgSrc = ta.getDrawable(R.styleable.TopBarSon_oneImgSrc);
        logoImgSrc = ta.getDrawable(R.styleable.TopBarSon_logoImgSrc);
        threeImgSrc = ta.getDrawable(R.styleable.TopBarSon_threeImgSrc);
        foreImgSrc = ta.getDrawable(R.styleable.TopBarSon_foreImgSrc);
        isVisibility = ta.getInteger(R.styleable.TopBarSon_foreVisibility, 0);
        ta.recycle();

        oneView = new ImageView(context);
        twoView = new View(context);
        threeView = new ImageView(context);
        foreView = new ImageView(context);
        linearLayout = new LinearLayout(context);
        logoView = new ImageView(context);

        // 为创建的组件元素赋值
        // 值就来源于我们在引用的xml文件中给对应属性的赋值
        oneView.setImageDrawable(oneImgSrc);
        oneView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        logoView.setImageDrawable(logoImgSrc);
        logoView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        threeView.setImageDrawable(threeImgSrc);
        threeView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        foreView.setImageDrawable(foreImgSrc);
        foreView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        switch (isVisibility) {
            case 0:
                foreView.setVisibility(View.VISIBLE);
                break;
            case 1:
                foreView.setVisibility(View.INVISIBLE);
                break;
            case 2:
                foreView.setVisibility(View.GONE);
                break;
            default:
                break;
        }

        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        oneParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        linearLayout.addView(oneView, 0, oneParams);

        twoParams = new LinearLayout.LayoutParams(0,LayoutParams.MATCH_PARENT);
        twoParams.weight = 1;
        linearLayout.addView(twoView, 1, twoParams);

        threeParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        linearLayout.addView(threeView, 2, threeParams);

        foreParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        linearLayout.addView(foreView, 3, foreParams);

        addView(linearLayout, 0);

        logoParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        logoParams.gravity = Gravity.CENTER;
        addView(logoView, 1, logoParams);

        oneView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.oneClick();
            }
        });
        logoView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.logoClick();
            }
        });
        threeView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.threeClick();
            }
        });
        foreView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.foreClick();
            }
        });
    }

    public void setOnTopbarClickListener(topbarClickListener mListener) {
        this.mListener = mListener;
    }

    public interface topbarClickListener {
        void oneClick();
        void logoClick();
        void threeClick();
        void foreClick();
    }

}
