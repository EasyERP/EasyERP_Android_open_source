package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.custom.views.sliding_pane_menu.SlidingPaneMenuLayout;

import org.androidannotations.annotations.EView;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/13/2017.)
 */
@EView
public class MenuDrawerContainer extends SlidingPaneMenuLayout {

    // First child must be MenuDrawerView, second is content. Max child's count is 2
    private MenuDrawerView menuDrawerView;
    private View contentView;

    private MenuDrawerState stateMenu;
    private int collapsedMenuWidth;
    private int expandedMenuWidth;

    public MenuDrawerContainer(Context context) {
        super(context);
        prepareAttributes(context, null, 0);
    }

    public MenuDrawerContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        prepareAttributes(context, attrs, 0);
    }

    public MenuDrawerContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        prepareAttributes(context, attrs, defStyleAttr);
    }

    private void prepareAttributes(final Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MenuDrawerContainer_, defStyleAttr, 0);
            setCollapsedMenuWidth((int) typedArray.getDimension(R.styleable.MenuDrawerContainer__collapsedMenuWidth, 0f));
            setExpandedMenuWidth((int) typedArray.getDimension(R.styleable.MenuDrawerContainer__expandedMenuWidth, 0f));
            setStateMenu(MenuDrawerState.valueOf(typedArray.getInt(R.styleable.MenuDrawerContainer__stateMenu, 0)));
            typedArray.recycle();
        }
    }

    public void setStateMenu(MenuDrawerState stateMenu) {
        this.stateMenu = stateMenu;
    }

    public MenuDrawerState getStateMenu() {
        return stateMenu;
    }

    public void setCollapsedMenuWidth(int collapsedMenuWidth) {
        this.collapsedMenuWidth = collapsedMenuWidth;
    }

    public void setExpandedMenuWidth(int expandedMenuWidth) {
        this.expandedMenuWidth = expandedMenuWidth;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final LayoutParams lp = (LayoutParams) contentView.getLayoutParams();
        lp.setMarginStart(collapsedMenuWidth);
        contentView.setLayoutParams(lp);
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (menuDrawerView != null && contentView != null) {
            measureChild(menuDrawerView, MeasureSpec.makeMeasureSpec(expandedMenuWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.EXACTLY));
            measureChild(contentView, MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY) - collapsedMenuWidth, MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.EXACTLY));
        }
    }

    private void checkAndPreparingChilds() {
        if (getChildCount() == 2) {
            if (getChildAt(0) instanceof MenuDrawerView) {
                menuDrawerView = (MenuDrawerView) getChildAt(0);
                contentView = getChildAt(1);
            } else throw new RuntimeException("First child view in MenuDrawerContainer must be MenuDrawerView");
        } else throw new RuntimeException("MenuDrawerContainer must contains 2 view components: 1. MenuDrawerView, 2. Any View ");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        super.setPanelSlideListener(slideMenuPanelListener);
        checkAndPreparingChilds();
        initStateMenu();
    }

    public void initStateMenu() {
        switch (stateMenu) {
            case OPEN:
                openPane();
                slideMenuPanelListener.onPanelOpened(this);
                break;
            case CLOSE:
                closePane();
                slideMenuPanelListener.onPanelClosed(this);
                break;
        }
    }

    private final PanelSlideListener slideMenuPanelListener = new PanelSlideListener() {
        @Override
        public void onPanelSlide(View panel, float slideOffset) {
            menuDrawerView.setSlideOffset(slideOffset);
        }

        @Override
        public void onPanelOpened(View panel) {
            menuDrawerView.setSlideOffset(1f);
            setStateMenu(MenuDrawerState.OPEN);
        }

        @Override
        public void onPanelClosed(View panel) {
            menuDrawerView.setSlideOffset(0f);
            setStateMenu(MenuDrawerState.CLOSE);
        }
    };

}
