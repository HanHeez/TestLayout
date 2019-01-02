package com.gtv.hanhee.testlayout.ui.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.gtv.hanhee.testlayout.R;
import com.zhouwei.mzbanner.CustomViewPager;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;
import com.zhouwei.mzbanner.transformer.CoverModeTransformer;
import com.zhouwei.mzbanner.transformer.ScaleYTransformer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductRecommendBanner<T> extends RelativeLayout {
    private static final String TAG = "ProductRecommendBanner";
    private CustomViewPager mViewPager;
    private ProductRecommendBanner.MZPagerAdapter mAdapter;
    private List<T> mDatas;
    private boolean mIsAutoPlay = false;
    private int mCurrentItem = 0;
    private Handler mHandler = new Handler();
    private int mDelayedTime = 3000;
    private ProductRecommendBanner.ViewPagerScroller mViewPagerScroller;
    private boolean mIsOpenMZEffect = true;
    private boolean mIsCanLoop = true;
    private LinearLayout mIndicatorContainer;
    private ArrayList<ImageView> mIndicators = new ArrayList();
    private int[] mIndicatorRes;
    private int mIndicatorPaddingLeft;
    private int mIndicatorPaddingRight;
    private int mIndicatorPaddingTop;
    private int mIndicatorPaddingBottom;
    private int mMZModePadding;
    private int mIndicatorAlign;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private ProductRecommendBanner.BannerPageClickListener mBannerPageClickListener;
    private boolean mIsMiddlePageCover;
    private final Runnable mLoopRunnable;

    public ProductRecommendBanner(@NonNull Context context) {
        super(context);
        this.mIndicatorRes = new int[]{R.drawable.indicator_product_recommend_normal, R.drawable.indicator_product_recommend_selected};
        this.mIndicatorPaddingLeft = 0;
        this.mIndicatorPaddingRight = 0;
        this.mIndicatorPaddingTop = 0;
        this.mIndicatorPaddingBottom = 0;
        this.mMZModePadding = 0;
        this.mIndicatorAlign = 1;
        this.mIsMiddlePageCover = true;
        this.mLoopRunnable = new Runnable() {
            public void run() {
                if (ProductRecommendBanner.this.mIsAutoPlay) {
                    ProductRecommendBanner.this.mCurrentItem = ProductRecommendBanner.this.mViewPager.getCurrentItem();
                    ProductRecommendBanner.this.mCurrentItem++;
                    if (ProductRecommendBanner.this.mCurrentItem == ProductRecommendBanner.this.mAdapter.getCount() - 1) {
                        ProductRecommendBanner.this.mCurrentItem = 0;
                        ProductRecommendBanner.this.mViewPager.setCurrentItem(ProductRecommendBanner.this.mCurrentItem, false);
                        ProductRecommendBanner.this.mHandler.postDelayed(this, (long)ProductRecommendBanner.this.mDelayedTime);
                    } else {
                        ProductRecommendBanner.this.mViewPager.setCurrentItem(ProductRecommendBanner.this.mCurrentItem);
                        ProductRecommendBanner.this.mHandler.postDelayed(this, (long)ProductRecommendBanner.this.mDelayedTime);
                    }
                } else {
                    ProductRecommendBanner.this.mHandler.postDelayed(this, (long)ProductRecommendBanner.this.mDelayedTime);
                }

            }
        };
        this.init();
    }

    public ProductRecommendBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mIndicatorRes = new int[]{R.drawable.indicator_product_recommend_normal, R.drawable.indicator_product_recommend_selected};
        this.mIndicatorPaddingLeft = 0;
        this.mIndicatorPaddingRight = 0;
        this.mIndicatorPaddingTop = 0;
        this.mIndicatorPaddingBottom = 0;
        this.mMZModePadding = 0;
        this.mIndicatorAlign = 1;
        this.mIsMiddlePageCover = true;
        this.mLoopRunnable = new Runnable() {
            public void run() {
                if (ProductRecommendBanner.this.mIsAutoPlay) {
                    ProductRecommendBanner.this.mCurrentItem = ProductRecommendBanner.this.mViewPager.getCurrentItem();
                    ProductRecommendBanner.this.mCurrentItem++;
                    if (ProductRecommendBanner.this.mCurrentItem == ProductRecommendBanner.this.mAdapter.getCount() - 1) {
                        ProductRecommendBanner.this.mCurrentItem = 0;
                        ProductRecommendBanner.this.mViewPager.setCurrentItem(ProductRecommendBanner.this.mCurrentItem, false);
                        ProductRecommendBanner.this.mHandler.postDelayed(this, (long)ProductRecommendBanner.this.mDelayedTime);
                    } else {
                        ProductRecommendBanner.this.mViewPager.setCurrentItem(ProductRecommendBanner.this.mCurrentItem);
                        ProductRecommendBanner.this.mHandler.postDelayed(this, (long)ProductRecommendBanner.this.mDelayedTime);
                    }
                } else {
                    ProductRecommendBanner.this.mHandler.postDelayed(this, (long)ProductRecommendBanner.this.mDelayedTime);
                }

            }
        };
        this.readAttrs(context, attrs);
        this.init();
    }

    public ProductRecommendBanner(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mIndicatorRes = new int[]{R.drawable.indicator_product_recommend_normal, R.drawable.indicator_product_recommend_selected};
        this.mIndicatorPaddingLeft = 0;
        this.mIndicatorPaddingRight = 0;
        this.mIndicatorPaddingTop = 0;
        this.mIndicatorPaddingBottom = 0;
        this.mMZModePadding = 0;
        this.mIndicatorAlign = 1;
        this.mIsMiddlePageCover = true;
        this.mLoopRunnable = new Runnable() {
            public void run() {
                if (ProductRecommendBanner.this.mIsAutoPlay) {
                    ProductRecommendBanner.this.mCurrentItem = ProductRecommendBanner.this.mViewPager.getCurrentItem();
                    ProductRecommendBanner.this.mCurrentItem++;
                    if (ProductRecommendBanner.this.mCurrentItem == ProductRecommendBanner.this.mAdapter.getCount() - 1) {
                        ProductRecommendBanner.this.mCurrentItem = 0;
                        ProductRecommendBanner.this.mViewPager.setCurrentItem(ProductRecommendBanner.this.mCurrentItem, false);
                        ProductRecommendBanner.this.mHandler.postDelayed(this, (long)ProductRecommendBanner.this.mDelayedTime);
                    } else {
                        ProductRecommendBanner.this.mViewPager.setCurrentItem(ProductRecommendBanner.this.mCurrentItem);
                        ProductRecommendBanner.this.mHandler.postDelayed(this, (long)ProductRecommendBanner.this.mDelayedTime);
                    }
                } else {
                    ProductRecommendBanner.this.mHandler.postDelayed(this, (long)ProductRecommendBanner.this.mDelayedTime);
                }

            }
        };
        this.readAttrs(context, attrs);
        this.init();
    }

    @RequiresApi(
            api = 21
    )
    public ProductRecommendBanner(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mIndicatorRes = new int[]{R.drawable.indicator_product_recommend_normal, R.drawable.indicator_product_recommend_selected};
        this.mIndicatorPaddingLeft = 0;
        this.mIndicatorPaddingRight = 0;
        this.mIndicatorPaddingTop = 0;
        this.mIndicatorPaddingBottom = 0;
        this.mMZModePadding = 0;
        this.mIndicatorAlign = 1;
        this.mIsMiddlePageCover = true;
        this.mLoopRunnable = new Runnable() {
            public void run() {
                if (ProductRecommendBanner.this.mIsAutoPlay) {
                    ProductRecommendBanner.this.mCurrentItem = ProductRecommendBanner.this.mViewPager.getCurrentItem();
                    ProductRecommendBanner.this.mCurrentItem++;
                    if (ProductRecommendBanner.this.mCurrentItem == ProductRecommendBanner.this.mAdapter.getCount() - 1) {
                        ProductRecommendBanner.this.mCurrentItem = 0;
                        ProductRecommendBanner.this.mViewPager.setCurrentItem(ProductRecommendBanner.this.mCurrentItem, false);
                        ProductRecommendBanner.this.mHandler.postDelayed(this, (long)ProductRecommendBanner.this.mDelayedTime);
                    } else {
                        ProductRecommendBanner.this.mViewPager.setCurrentItem(ProductRecommendBanner.this.mCurrentItem);
                        ProductRecommendBanner.this.mHandler.postDelayed(this, (long)ProductRecommendBanner.this.mDelayedTime);
                    }
                } else {
                    ProductRecommendBanner.this.mHandler.postDelayed(this, (long)ProductRecommendBanner.this.mDelayedTime);
                }

            }
        };
        this.readAttrs(context, attrs);
        this.init();
    }

    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MZBannerView);
        this.mIsOpenMZEffect = typedArray.getBoolean(R.styleable.MZBannerView_open_mz_mode, true);
        this.mIsMiddlePageCover = typedArray.getBoolean(R.styleable.MZBannerView_middle_page_cover, true);
        this.mIsCanLoop = typedArray.getBoolean(R.styleable.MZBannerView_canLoop, true);
        this.mIndicatorAlign = typedArray.getInt(R.styleable.MZBannerView_indicatorAlign, ProductRecommendBanner.IndicatorAlign.CENTER.ordinal());
        this.mIndicatorPaddingLeft = typedArray.getDimensionPixelSize(R.styleable.MZBannerView_indicatorPaddingLeft, 0);
        this.mIndicatorPaddingRight = typedArray.getDimensionPixelSize(R.styleable.MZBannerView_indicatorPaddingRight, 0);
        this.mIndicatorPaddingTop = typedArray.getDimensionPixelSize(R.styleable.MZBannerView_indicatorPaddingTop, 0);
        this.mIndicatorPaddingBottom = typedArray.getDimensionPixelSize(R.styleable.MZBannerView_indicatorPaddingBottom, 0);
        typedArray.recycle();
    }

    private void init() {
        View view = null;
        if (this.mIsOpenMZEffect) {
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.custom_mzbanner_effect_layout, this, true);
        } else {
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.custom_mzbanner_normal_layout, this, true);
        }

        this.mIndicatorContainer = (LinearLayout)view.findViewById(R.id.banner_indicator_container);
        this.mViewPager = (CustomViewPager)view.findViewById(R.id.mzbanner_vp);
        this.mViewPager.setOffscreenPageLimit(4);
        this.mMZModePadding = dpToPx(30);
        this.initViewPagerScroll();
        this.sureIndicatorPosition();
    }

    private void setOpenMZEffect() {
        if (this.mIsOpenMZEffect) {
            if (this.mIsMiddlePageCover) {
                this.mViewPager.setPageTransformer(true, new CoverModeTransformer(this.mViewPager));
            } else {
                this.mViewPager.setPageTransformer(false, new ScaleYTransformer());
            }
        }

    }

    private void sureIndicatorPosition() {
        if (this.mIndicatorAlign == ProductRecommendBanner.IndicatorAlign.LEFT.ordinal()) {
            this.setIndicatorAlign(ProductRecommendBanner.IndicatorAlign.LEFT);
        } else if (this.mIndicatorAlign == ProductRecommendBanner.IndicatorAlign.CENTER.ordinal()) {
            this.setIndicatorAlign(ProductRecommendBanner.IndicatorAlign.CENTER);
        } else {
            this.setIndicatorAlign(ProductRecommendBanner.IndicatorAlign.RIGHT);
        }

    }

    private void initViewPagerScroll() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            this.mViewPagerScroller = new ProductRecommendBanner.ViewPagerScroller(this.mViewPager.getContext());
            mScroller.set(this.mViewPager, this.mViewPagerScroller);
        } catch (NoSuchFieldException var2) {
            var2.printStackTrace();
        } catch (IllegalArgumentException var3) {
            var3.printStackTrace();
        } catch (IllegalAccessException var4) {
            var4.printStackTrace();
        }

    }

    private void initIndicator() {
        this.mIndicatorContainer.removeAllViews();
        this.mIndicators.clear();

        for(int i = 0; i < this.mDatas.size(); ++i) {
            ImageView imageView = new ImageView(this.getContext());
            int paddingRight;
            if (this.mIndicatorAlign == ProductRecommendBanner.IndicatorAlign.LEFT.ordinal()) {
                if (i == 0) {
                    paddingRight = this.mIsOpenMZEffect ? this.mIndicatorPaddingLeft + this.mMZModePadding : this.mIndicatorPaddingLeft;
                    imageView.setPadding(paddingRight + 6, 0, 6, 0);
                } else {
                    imageView.setPadding(6, 0, 6, 0);
                }
            } else if (this.mIndicatorAlign == ProductRecommendBanner.IndicatorAlign.RIGHT.ordinal()) {
                if (i == this.mDatas.size() - 1) {
                    paddingRight = this.mIsOpenMZEffect ? this.mMZModePadding + this.mIndicatorPaddingRight : this.mIndicatorPaddingRight;
                    imageView.setPadding(6, 0, 6 + paddingRight, 0);
                } else {
                    imageView.setPadding(6, 0, 6, 0);
                }
            } else {
                imageView.setPadding(6, 0, 6, 0);
            }

            if (i == this.mCurrentItem % this.mDatas.size()) {
                imageView.setImageResource(this.mIndicatorRes[1]);
            } else {
                imageView.setImageResource(this.mIndicatorRes[0]);
            }

            this.mIndicators.add(imageView);
            this.mIndicatorContainer.addView(imageView);
        }

    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!this.mIsCanLoop) {
            return super.dispatchTouchEvent(ev);
        } else {
            switch(ev.getAction()) {
                case 0:
                case 2:
                case 3:
                case 4:
                    int paddingLeft = this.mViewPager.getLeft();
                    float touchX = ev.getRawX();
                    if (touchX >= (float)paddingLeft && touchX < (float)(getScreenWidth(this.getContext()) - paddingLeft)) {
                        this.pause();
                    }
                    break;
                case 1:
                    this.start();
            }

            return super.dispatchTouchEvent(ev);
        }
    }

    public static int getScreenWidth(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        return width;
    }

    public void start() {
        if (this.mAdapter != null) {
            if (this.mIsCanLoop) {
                this.pause();
                this.mIsAutoPlay = false;
                this.mHandler.postDelayed(this.mLoopRunnable, (long)this.mDelayedTime);
            }

        }
    }

    public void pause() {
        this.mIsAutoPlay = false;
        this.mHandler.removeCallbacks(this.mLoopRunnable);
    }

    public void setCanLoop(boolean canLoop) {
        this.mIsCanLoop = canLoop;
        if (!canLoop) {
            this.pause();
        }

    }

    public void setDelayedTime(int delayedTime) {
        this.mDelayedTime = delayedTime;
    }

    public void addPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }

    public void setBannerPageClickListener(ProductRecommendBanner.BannerPageClickListener bannerPageClickListener) {
        this.mBannerPageClickListener = bannerPageClickListener;
    }

    public void setIndicatorVisible(boolean visible) {
        if (visible) {
            this.mIndicatorContainer.setVisibility(VISIBLE);
        } else {
            this.mIndicatorContainer.setVisibility(GONE);
        }

    }

    public void setIndicatorPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        this.mIndicatorPaddingLeft = paddingLeft;
        this.mIndicatorPaddingTop = paddingTop;
        this.mIndicatorPaddingRight = paddingRight;
        this.mIndicatorPaddingBottom = paddingBottom;
        this.sureIndicatorPosition();
    }

    public ViewPager getViewPager() {
        return this.mViewPager;
    }

    public void setIndicatorRes(@DrawableRes int unSelectRes, @DrawableRes int selectRes) {
        this.mIndicatorRes[0] = unSelectRes;
        this.mIndicatorRes[1] = selectRes;
    }

    public void setPages(List<T> datas, MZHolderCreator mzHolderCreator) {
        if (datas != null && mzHolderCreator != null) {
            this.mDatas = datas;
            this.pause();
            if (datas.size() < 3) {
                this.mIsOpenMZEffect = false;
                MarginLayoutParams layoutParams = (MarginLayoutParams)this.mViewPager.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, 0);
                this.mViewPager.setLayoutParams(layoutParams);
                this.setClipChildren(true);
                this.mViewPager.setClipChildren(true);
            }

            this.setOpenMZEffect();
            this.initIndicator();
            this.mAdapter = new ProductRecommendBanner.MZPagerAdapter(datas, mzHolderCreator, this.mIsCanLoop);
            this.mAdapter.setUpViewViewPager(this.mViewPager);
            this.mAdapter.setPageClickListener(this.mBannerPageClickListener);
            this.mViewPager.clearOnPageChangeListeners();
            this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    int realPosition = position % ProductRecommendBanner.this.mIndicators.size();
                    if (ProductRecommendBanner.this.mOnPageChangeListener != null) {
                        ProductRecommendBanner.this.mOnPageChangeListener.onPageScrolled(realPosition, positionOffset, positionOffsetPixels);
                    }

                }

                public void onPageSelected(int position) {
                    ProductRecommendBanner.this.mCurrentItem = position;
                    int realSelectPosition = ProductRecommendBanner.this.mCurrentItem % ProductRecommendBanner.this.mIndicators.size();

                    for(int i = 0; i < ProductRecommendBanner.this.mDatas.size(); ++i) {
                        if (i == realSelectPosition) {
                            ((ImageView)ProductRecommendBanner.this.mIndicators.get(i)).setImageResource(ProductRecommendBanner.this.mIndicatorRes[1]);
                        } else {
                            ((ImageView)ProductRecommendBanner.this.mIndicators.get(i)).setImageResource(ProductRecommendBanner.this.mIndicatorRes[0]);
                        }
                    }

                    if (ProductRecommendBanner.this.mOnPageChangeListener != null) {
                        ProductRecommendBanner.this.mOnPageChangeListener.onPageSelected(realSelectPosition);
                    }

                }

                public void onPageScrollStateChanged(int state) {
                    switch(state) {
                        case 1:
                            ProductRecommendBanner.this.mIsAutoPlay = false;
                            break;
                        case 2:
                            ProductRecommendBanner.this.mIsAutoPlay = false;
                    }

                    if (ProductRecommendBanner.this.mOnPageChangeListener != null) {
                        ProductRecommendBanner.this.mOnPageChangeListener.onPageScrollStateChanged(state);
                    }

                }
            });
        }
    }

    public void setIndicatorAlign(ProductRecommendBanner.IndicatorAlign indicatorAlign) {
        this.mIndicatorAlign = indicatorAlign.ordinal();
        RelativeLayout.LayoutParams layoutParams = (LayoutParams)this.mIndicatorContainer.getLayoutParams();
        if (indicatorAlign == ProductRecommendBanner.IndicatorAlign.LEFT) {
            layoutParams.addRule(9);
        } else if (indicatorAlign == ProductRecommendBanner.IndicatorAlign.RIGHT) {
            layoutParams.addRule(11);
        } else {
            layoutParams.addRule(14);
        }

        layoutParams.setMargins(0, this.mIndicatorPaddingTop, 0, this.mIndicatorPaddingBottom);
        this.mIndicatorContainer.setLayoutParams(layoutParams);
    }

    public LinearLayout getIndicatorContainer() {
        return this.mIndicatorContainer;
    }

    public void setDuration(int duration) {
        this.mViewPagerScroller.setDuration(duration);
    }

    public void setUseDefaultDuration(boolean useDefaultDuration) {
        this.mViewPagerScroller.setUseDefaultDuration(useDefaultDuration);
    }

    public int getDuration() {
        return this.mViewPagerScroller.getScrollDuration();
    }

    public static int dpToPx(int dp) {
        return (int)TypedValue.applyDimension(1, (float)dp, Resources.getSystem().getDisplayMetrics());
    }

    public interface BannerPageClickListener {
        void onPageClick(View var1, int var2);
    }

    public static class ViewPagerScroller extends Scroller {
        private int mDuration = 800;
        private boolean mIsUseDefaultDuration = false;

        public ViewPagerScroller(Context context) {
            super(context);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, this.mDuration);
        }

        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, this.mIsUseDefaultDuration ? duration : this.mDuration);
        }

        public void setUseDefaultDuration(boolean useDefaultDuration) {
            this.mIsUseDefaultDuration = useDefaultDuration;
        }

        public boolean isUseDefaultDuration() {
            return this.mIsUseDefaultDuration;
        }

        public void setDuration(int duration) {
            this.mDuration = duration;
        }

        public int getScrollDuration() {
            return this.mDuration;
        }
    }

    public static class MZPagerAdapter<T> extends PagerAdapter {
        private List<T> mDatas;
        private MZHolderCreator mMZHolderCreator;
        private ViewPager mViewPager;
        private boolean canLoop;
        private ProductRecommendBanner.BannerPageClickListener mPageClickListener;
        private final int mLooperCountFactor = 500;

        public MZPagerAdapter(List<T> datas, MZHolderCreator MZHolderCreator, boolean canLoop) {
            if (this.mDatas == null) {
                this.mDatas = new ArrayList();
            }

            Iterator var4 = datas.iterator();

            while(var4.hasNext()) {
                T t = (T) var4.next();
                this.mDatas.add(t);
            }

            this.mMZHolderCreator = MZHolderCreator;
            this.canLoop = canLoop;
        }

        public void setPageClickListener(ProductRecommendBanner.BannerPageClickListener pageClickListener) {
            this.mPageClickListener = pageClickListener;
        }

        public void setUpViewViewPager(ViewPager viewPager) {
            this.mViewPager = viewPager;
            this.mViewPager.setAdapter(this);
            this.mViewPager.getAdapter().notifyDataSetChanged();
            int currentItem = this.canLoop ? this.getStartSelectItem() : 0;
            this.mViewPager.setCurrentItem(currentItem);
        }

        private int getStartSelectItem() {
            if (this.getRealCount() == 0) {
                return 0;
            } else {
                int currentItem = this.getRealCount() * 500 / 2;
                if (currentItem % this.getRealCount() == 0) {
                    return currentItem;
                } else {
                    while(currentItem % this.getRealCount() != 0) {
                        ++currentItem;
                    }

                    return currentItem;
                }
            }
        }

        public void setDatas(List<T> datas) {
            this.mDatas = datas;
        }

        public int getCount() {
            return this.canLoop ? this.getRealCount() * 500 : this.getRealCount();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public Object instantiateItem(ViewGroup container, int position) {
            View view = this.getView(position, container);
            container.addView(view);
            return view;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        public void finishUpdate(ViewGroup container) {
            if (this.canLoop) {
                int position = this.mViewPager.getCurrentItem();
                if (position == this.getCount() - 1) {
                    position = 0;
                    this.setCurrentItem(position);
                }
            }

        }

        private void setCurrentItem(int position) {
            try {
                this.mViewPager.setCurrentItem(position, false);
            } catch (IllegalStateException var3) {
                var3.printStackTrace();
            }

        }

        private int getRealCount() {
            return this.mDatas == null ? 0 : this.mDatas.size();
        }

        private View getView(int position, ViewGroup container) {
            final int realPosition = position % this.getRealCount();
            MZViewHolder holder = null;
            holder = this.mMZHolderCreator.createViewHolder();
            if (holder == null) {
                throw new RuntimeException("can not return a null holder");
            } else {
                View view = holder.createView(container.getContext());
                if (this.mDatas != null && this.mDatas.size() > 0) {
                    holder.onBind(container.getContext(), realPosition, this.mDatas.get(realPosition));
                }

                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        if (ProductRecommendBanner.MZPagerAdapter.this.mPageClickListener != null) {
                            ProductRecommendBanner.MZPagerAdapter.this.mPageClickListener.onPageClick(v, realPosition);
                        }

                    }
                });
                return view;
            }
        }
    }

    public static enum IndicatorAlign {
        LEFT,
        CENTER,
        RIGHT;

        private IndicatorAlign() {
        }
    }
}
