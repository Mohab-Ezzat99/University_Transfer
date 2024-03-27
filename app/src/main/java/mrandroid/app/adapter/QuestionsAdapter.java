package mrandroid.app.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;
import mrandroid.app.R;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.MedicineViewHolder> {
    private List<String> list = new ArrayList<>();
    private OnItemClickListener listener;

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicineViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_doctor, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        String item = list.get(holder.getAdapterPosition());

        holder.et_question.setText(item);

        holder.ivEdit.setOnClickListener(view -> {
            listener.onEditClick(item);
        });

        holder.ivDelete.setOnClickListener(view -> {
            listener.onDeleteClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setList(List<String> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    static class MedicineViewHolder extends RecyclerView.ViewHolder {

        private final TextInputEditText et_question;
        private final ImageView ivEdit;
        private final ImageView ivDelete;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);

            et_question = itemView.findViewById(R.id.et_question);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);

        }
    }

    public interface OnItemClickListener {
        void onEditClick(String item);
        void onDeleteClick(String item);
    }
}