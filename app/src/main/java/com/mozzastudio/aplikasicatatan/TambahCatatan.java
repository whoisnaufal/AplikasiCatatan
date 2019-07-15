package com.mozzastudio.aplikasicatatan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import javax.microedition.khronos.egl.EGLDisplay;

public class TambahCatatan extends AppCompatActivity {

    EditText etNama, etCatatan;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_catatan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etNama = (EditText) findViewById(R.id.etNamafile);
        etCatatan = (EditText) findViewById(R.id.etCatatan);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File path = getDir("NOTES",MODE_PRIVATE);
                File file = new File(path.toString(), etNama.getText().toString());

                FileOutputStream fileOutputStream = null;

                try{
                    file.createNewFile();
                    fileOutputStream = new FileOutputStream(file,false);
                    fileOutputStream.write(etCatatan.getText().toString().getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    Toast.makeText(getApplicationContext(),"Catatan tersimpan",Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
