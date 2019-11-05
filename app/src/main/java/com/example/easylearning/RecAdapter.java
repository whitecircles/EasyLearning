package com.example.easylearning;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {

        private LayoutInflater inflater;
        private List<Item> items;
        private Button btnForEdit;
        private Button btnForDel;
        private TextView result;
        Intent intent;
        Context cst;

        RecAdapter(Context context, List<Item> items) {
            this.items = items;
            this.inflater = LayoutInflater.from(context);
            intent = new Intent(context, SubActivityToAnalize.class);
            cst = context;
        }
        @Override
        public RecAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.fragment, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecAdapter.ViewHolder holder, int position) {
            Item item = items.get(position);

            if (item.isLanguage() == true)
            {
                result.setText(item.getTheword());
            }
            else
            {
                result.setText(item.getSlovo());
            }


            btnForEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("id", item.getId());
                    cst.startActivity(intent);
                }
            });

            btnForDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppDatabase db =  Room.databaseBuilder(cst,
                            AppDatabase.class, "database").allowMainThreadQueries().build();

                    WordDao itemsDao = db.wordDao();

                    itemsDao.delete(itemsDao.getById(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            final TextView result;
            ViewHolder(View view){
                super(view);
                btnForEdit = (Button)view.findViewById(R.id.buttonForEdit);

                btnForDel = (Button)view.findViewById(R.id.buttonForDel);
                result = (TextView) view.findViewById(R.id.text);

            }
        }
    }

