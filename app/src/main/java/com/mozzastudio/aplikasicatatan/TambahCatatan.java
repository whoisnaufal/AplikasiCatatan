package com.mozzastudio.aplikasicatatan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class TambahCatatan extends AppCompatActivity {

    EditText etNama, etCatatan;
    Button btnSimpan;
    String fileName = "";
    String tempCatatan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_catatan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etNama = (EditText) findViewById(R.id.etNamafile);
        etCatatan = (EditText) findViewById(R.id.etCatatan);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fileName = extras.getString("filename");
            etNama.setText(fileName);
            getSupportActionBar().setTitle("Ubah Catatan");
            bacaFile();
        } else {
            getSupportActionBar().setTitle("Tambah Catatan");
        }

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FILENAME = etNama.getText().toString();
                String isi = etCatatan.getText().toString();
                File path = getDir("NOTES", MODE_PRIVATE);
                File file = new File(path, FILENAME);
                FileOutputStream fileOutputStream = null;
                try {
                    file.createNewFile();
                    fileOutputStream = new FileOutputStream(file, false);
                    fileOutputStream.write(isi.getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    Toast.makeText(getApplicationContext(), "Catatan tersimpan", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                onBackPressed();
            }
        });
    }

    void bacaFile(){
        File path = getDir("NOTES",MODE_PRIVATE);
        File file = new File(path, etNama.getText().toString());
        if (file.exists()){
            StringBuilder text = new StringBuilder();

            try{
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                String line = bufferedReader.readLine();

                while (line != null){
                    text.append(line);
                    line =bufferedReader.readLine();
                }
                bufferedReader.close();
            }catch (IOException e){
                System.out.println("Error " + e.getMessage());
            }
            tempCatatan = text.toString();
            etCatatan.setText(text.toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
