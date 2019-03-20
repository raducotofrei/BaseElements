package ch.rcotofrei.baseelements;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import java.util.List;

public class BaseListElement<S> extends LinearLayout {

    RecyclerView recyclerView;

    BaseListController<S> mListController;

    public BaseListElement(Context context) {
        super(context);
        initComponents();
    }

    public BaseListElement(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initComponents();
    }

    public BaseListElement(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComponents();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseListElement(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initComponents();
    }

    private void initComponents() {
        inflate(getContext(), R.layout.component_base_list, this);

        recyclerView = findViewById(R.id.recycler_view);
    }

    public void setListController(@NonNull BaseListController<S> listController) {
        this.mListController = listController;
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        if (recyclerView == null || mListController == null) {
            return;
        }

        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.setAdapter(mListController.getAdapter());
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    public void addItem(S item) {
        if (mListController != null) {
            mListController.getAdapter().addElement(item);
        }
    }

    public void setItems(List<S> items) {
        if (mListController != null) {
            mListController.getAdapter().addElements(items);
        }
    }


}
