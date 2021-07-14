package com.example.assignment2.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.R;
import com.example.assignment2.activities.MainActivity;
//import com.example.assignment2.activities.QuestionActivity;
import com.example.assignment2.activities.QuestionActivity;
import com.example.assignment2.models.Quiz;
import com.example.assignment2.utils.ColorPicker;
import com.example.assignment2.utils.IconPicker;
//import com.example.assignment2.utils.ColorPicker;
//import com.example.assignment2.utils.IconPicker;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    Context context;
    ArrayList<Quiz> quizList;

    public QuizAdapter(Context context, ArrayList<Quiz> quizList) {
        this.context = context;
        this.quizList = quizList;
    }


    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false);
        return new QuizViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onBindViewHolder(QuizAdapter.QuizViewHolder holder, int position) {
        holder.textView.setText(quizList.get(position).getTitle());
        holder.imageView.setImageResource(IconPicker.getIcon());
        holder.cardView.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,quizList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, QuestionActivity.class);
                intent.putExtra("DATE",quizList.get(position).getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public static class QuizViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView textView;
        ImageView imageView;
        CardView cardView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public QuizViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.quizTitle);
            imageView = (ImageView) itemView.findViewById(R.id.quizIcon);
            cardView = (CardView) itemView.findViewById(R.id.cardContainer);
        }
    }
}
