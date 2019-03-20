package ch.rcotofrei.baseelements;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseListController<S> {

    private BaseListAdapter mAdapter;

    public abstract int getItemLayout(int viewType);

    public abstract BaseListViewHolder getViewHolder(View itemView);

    @NonNull
    BaseListAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new BaseListAdapter();
        }
        return mAdapter;
    }

    public class BaseListAdapter extends RecyclerView.Adapter<BaseListViewHolder> {

        private ArrayList<S> elements;

        @Override
        public int getItemCount() {
            return elements.size();
        }

        @Override
        public void onBindViewHolder(@NonNull BaseListViewHolder holder, int position) {
            checkListNotNull();
            if (elements.get(position) != null) {
                holder.display(elements.get(position));
            }
        }

        @NonNull
        @Override
        public BaseListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(getItemLayout(viewType), parent, false);
            return getViewHolder(itemView);
        }

        void addElement(S element) {
            checkListNotNull();
            this.elements.add(element);
            notifyItemInserted(elements.indexOf(element));
        }

        void addElements(List<S> elements) {
            checkListNotNull();

            int firstElement = this.elements.size();
            this.elements.addAll(elements);
            notifyItemRangeInserted(firstElement, elements.size());
        }

        void deleteElement(S element) {
            checkListNotNull();
            int index = this.elements.indexOf(element);
            if (index >= 0) {
                this.elements.remove(index);
                notifyItemRemoved(index);
            }
        }

        void updateElement(int index, S element) {
            checkListNotNull();
            this.elements.set(index, element);
            notifyItemChanged(index);
        }

        private void checkListNotNull() {
            if (this.elements == null) {
                this.elements = new ArrayList<>();
            }
        }
    }

    public abstract class BaseListViewHolder extends RecyclerView.ViewHolder {

        public BaseListViewHolder(View itemView) {
            super(itemView);
            initComponents(itemView);
        }

        public abstract void display(S element);

        public abstract void initComponents(View itemView);
    }
}
