package com.si61.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDestinasi extends RecyclerView.Adapter<AdapterDestinasi.ViewHolderDestinasi>{
    private Context ctx;
    private ArrayList arrId, arrNama, arrALamat, arrJam;

    public AdapterDestinasi(Context ctx,ArrayList arrId, ArrayList arrNama, ArrayList arrALamat, ArrayList arrJam) {
        this.ctx = ctx;
        this.arrId = arrId;
        this.arrNama = arrNama;
        this.arrALamat = arrALamat;
        this.arrJam = arrJam;
    }

    @NonNull
    @Override
    public ViewHolderDestinasi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_destinasi, parent, false);
        return new ViewHolderDestinasi(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDestinasi holder, int position) {
        holder.tvId.setText(arrId.get(position).toString());
        holder.tvNama.setText(arrNama.get(position).toString());
        holder.tvAlamat.setText(arrALamat.get(position).toString());
        holder.tvJam.setText(arrJam.get(position).toString());

    }
    @Override
    public int getItemCount() {
        return arrNama.size();
    }

    public class ViewHolderDestinasi extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvAlamat, tvJam;

        public ViewHolderDestinasi(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvJam = itemView.findViewById(R.id.tv_jam);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Perintah Apa Yang Akan Dilakukan");
                    pesan.setCancelable(true);

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String id, nama, alamat, jam;

                            id = tvId.getText().toString();
                            nama = tvNama.getText().toString();
                            alamat = tvAlamat.getText().toString();
                            jam = tvJam.getText().toString();

                            Intent kirim = new Intent(ctx, UbahActivity.class);
                            kirim.putExtra("xId", id);
                            kirim.putExtra("xNama", nama);
                            kirim.putExtra("xAlamat", alamat);
                            kirim.putExtra("xJam", jam);
                            ctx.startActivity(kirim);
                        }
                    });
                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDatabaseHelper myDB = new MyDatabaseHelper(ctx);

                            long eks = myDB.hapusData(tvId.getText().toString());

                            if(eks == -1){
                                Toast.makeText(ctx, "Gagal Hapus Data!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ctx, "Sukses Hapus Data", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                ((MainActivity) ctx).onResume();
                            }
                        }
                    });

                    pesan.show();
                    return false;
                }
            });
        }
    }


}
