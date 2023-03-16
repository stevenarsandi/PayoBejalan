package com.si61.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etAlamat, etJam;
    private Button btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etJam = findViewById(R.id.et_jam);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama, alamat, jam;

                nama = etNama.getText().toString();
                alamat = etAlamat.getText().toString();
                jam = etJam.getText().toString();

                //trim untuk menambahkan spasi
                if(nama.trim().equals("")){
                    etNama.setError("Nama Tidak Boleh Kosong");
                }if(alamat.trim().equals("")){
                    etAlamat.setError("Alamat Tidak Boleh Kosong");
                }if(jam.trim().equals("")){
                    etJam.setError("Jam Tidak Boleh Kosong");
                }
                else{
                    MyDatabaseHelper myDB = new MyDatabaseHelper(TambahActivity.this);
                    long eks = myDB.tambahData(nama, alamat, jam);

                    if (eks == -1){
                        Toast.makeText(TambahActivity.this, "Tambah Data Gagal", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(TambahActivity.this, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show();
                        //finish menyelesaikan activity
                        finish();
                    }
                }
            }
        });
    }
}