package com.example.ativiadade01a;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String menu[] = new String[] {"Minha casas em Teofilo Otoni", "Minha casa em Viçosa", "DPI UFV", "Fechar aplicativo"};
        ArrayAdapter<String> arrAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(arrAdapt);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String aux = l.getItemAtPosition(position).toString();
        Intent it = new Intent(this, MostraMapaActivity.class);
        it.putExtra("option", aux);
        startActivity(it);
    }
}