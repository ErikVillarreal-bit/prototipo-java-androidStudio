package com.example.proyecto;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Utilidades.Utilidades;
import com.example.proyecto.entidades.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private EditText folio,nombre,edad,appFavorita;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hola!!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_datos, R.id.nav_mostrar_datos, R.id.about)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        conn = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void registerData(){
        folio = (EditText)findViewById(R.id.edt_folio);
        nombre = (EditText)findViewById(R.id.edt_nombre);
        edad = (EditText)findViewById(R.id.edt_edad);
        appFavorita = (EditText)findViewById(R.id.edt_appFavorita);

        conn = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_FOLIO,folio.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,nombre.getText().toString());
        values.put(Utilidades.CAMPO_EDAD,edad.getText().toString());
        values.put(Utilidades.CAMPO_APPFAVORITA,appFavorita.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_USUARIOS,Utilidades.CAMPO_FOLIO,values);
        Toast.makeText(getApplicationContext(),"Id de registro "+idResultante, Toast.LENGTH_LONG).show();

    }

    private void showData(){
        folio = (EditText)findViewById(R.id.caja_id_consulta);
        nombre = (EditText)findViewById(R.id.caja_nombre_consulta);
        edad = (EditText)findViewById(R.id.caja_edad_consulta);
        appFavorita = (EditText)findViewById(R.id.caja_appFavorita_consulta);

        conn = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();

        String[] parametros = {folio.getText().toString()};
        String[] campos = {Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_EDAD,Utilidades.CAMPO_APPFAVORITA};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIOS, campos,Utilidades.CAMPO_FOLIO+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            nombre.setText(cursor.getString(0));
            edad.setText(cursor.getString(1));
            appFavorita.setText(cursor.getString(2));
            cursor.close();
            Toast.makeText(getApplicationContext(),"Busqueda exitosa!!",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El usuario no existe!!",Toast.LENGTH_LONG).show();
            limpiar();
        }


    }

    private void limpiar() {
        nombre.setText("");
        edad.setText("");
        appFavorita.setText("");
    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_guardar_datos:
                registerData();
                break;
            case R.id.btn_consultar_info:
                showData();
                break;
        }
    }
}
