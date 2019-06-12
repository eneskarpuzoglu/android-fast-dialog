package karpuzoglu.enes.com.fastdialog;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {

    private Context context;
    private boolean isTablet;
    private List<FolderButton> activeButtons;
    private CustomItemClickListener clickListener;

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            itemIcon = itemView.findViewById(R.id.item_icon);

        }
    }

    public FolderAdapter(Context context, boolean isTablet) {
        this.activeButtons = new ArrayList<>();
        this.context = context;
        this.isTablet = isTablet;

    }
    public void setClickListener(CustomItemClickListener listener){
        clickListener = listener;
    }
    public void setActiveButtons(List<FolderButton> buttonList){
        for (int i=0;i<buttonList.size();i++){
            if (buttonList.get(i).isActive()){
                activeButtons.add(buttonList.get(i));
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_folder, parent, false);
        return new ViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        FolderButton footerButton = activeButtons.get(position);
        holder.itemIcon.setImageResource(footerButton.getDrawableId());
        holder.itemIcon.setTag(footerButton.getId());
        holder.itemIcon.setOnClickListener(v -> {
            if (clickListener != null)
                clickListener.onItemClick(v,position);
        });
    }

    @Override
    public int getItemCount() {
        return activeButtons.size();
    }

}