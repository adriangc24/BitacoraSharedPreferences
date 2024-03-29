package com.example.bitacorasharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.editText);
        String[] archivos = fileList();

        if(ArchivoExiste(archivos, "bitacora.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("bitacora.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String bitacoraCompleta="";

                while(linea!=null){
                    bitacoraCompleta = bitacoraCompleta + linea + "\n";
                    linea = br.readLine();
                }
                br .close();
                archivo.close();
                et1.setText(bitacoraCompleta);
            }
            catch(IOException e){

            }
        }
    }
    // Metodo check if files exists
    private boolean ArchivoExiste (String[] archivos,String nombreArchivo){
        for(int i=0;i<archivos.length;i++)
            if(nombreArchivo.equals(archivos[i]))
                return true;
            return false;

    }

    // Metodo boton guardar
    public void guardar(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            archivo.write(et1.getText().toString());
            archivo.flush();
            archivo.close();

        }catch(IOException e){

        }
        Toast.makeText(this,"Biotacora guardada",Toast.LENGTH_LONG).show();
        finish();
    }

}
