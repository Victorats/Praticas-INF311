package com.example.ativiadade01a;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
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
        if(aux.equals("Fechar aplicativo")){
            finish();
        }
        Pair<Double, Double> coord = retornaCoordernada(aux);
        it.putExtra("x",coord.first);
        it.putExtra("y",coord.second);
        startActivity(it);
    }

    protected android.util.Pair<Double, Double> retornaCoordernada(String local){
        if(local == "Minha casas em Teofilo Otoni"){
            return new Pair<>(-17.854417, -41.508195);
        }
        else if(local == "Minha casa em Viçosa"){
            return new Pair<>(-20.755195831967093, -42.876022587753255);
        }
        else if(local == "DPI UFV"){
            return new Pair<>(-20.76482710197161, -42.86845007536529);
        }
        return null;
    }
}