package com.example.pokdexcreando;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokdexcreando.models.Pokemon;
import com.google.android.material.circularreveal.CircularRevealLinearLayout;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class    GenerationAdapter extends  RecyclerView.Adapter<GenerationAdapter.ViewHolder> {
    private static final String TAG = "POKEDEX";
    private ArrayList<Pokemon> dataListG = new ArrayList<>();
    private Context context2;

    //test count
    private OnItemClickListener positionlister;
    public interface OnItemClickListener{
        void CountClick(int position);

    }
    public   void setoncountlister(OnItemClickListener listener){
        positionlister= listener;
    }

    //end
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listgeneration,parent,false);
        return new ViewHolder(view);
    }
    public GenerationAdapter(Context context,OnItemClickListener listener) {
        this.positionlister=listener;
        this.dataListG =new ArrayList<>() ;
        this.context2 = context;
    }

    @Override
    public void onBindViewHolder(@NonNull GenerationAdapter.ViewHolder holder, int position) {
        Pokemon p = dataListG.get(position);
        holder.textview.setText(p.getName());
//        holder.image.append(p.getName());
    }

    @Override
    public int getItemCount() {
        return dataListG.size();
    }

    public void adListGeneration(ArrayList<Pokemon> listgeneration) {
        this.dataListG=listgeneration;
        //Log.d(TAG,"ben"+dataset.get(0));
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView textview;
   CircleImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           textview= itemView.findViewById(R.id.textview);
            image = (CircleImageView) itemView.findViewById(R.id.profile_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context2,"Show your text here",Toast.LENGTH_SHORT).show();
                        int position = getAdapterPosition();
                        Log.e(TAG, String.valueOf(position)+"mama");
                            positionlister.CountClick(position);
                            //    Log.e(TAG, String.valueOf(position));



                    //start new intent
                }
            });

        }

    }

}
