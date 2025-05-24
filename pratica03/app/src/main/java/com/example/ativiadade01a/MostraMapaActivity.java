package com.example.ativiadade01a;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.util.Log; // Importar a classe Log
import android.widget.Toast; //mensagens Toast

public class MostraMapaActivity extends AppCompatActivity implements OnMapReadyCallback {
    private final LatLng minhaCasaTO = new LatLng(-17.854417, -41.508195);
    private final LatLng minhaCasaVC = new LatLng(-20.755195831967093, -42.876022587753255);
    private final LatLng dpiUFV = new LatLng(-20.76482710197161, -42.86845007536529);
    private static final String TAG = "MostraMapaActivity";
    private GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Iniciando MostraMapaActivity"); // Log no início do onCreate
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mostra_mapa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent it = getIntent();
        String option = it.getStringExtra("option");

        SupportMapFragment mapFrag = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFrag == null) {
            Log.e(TAG, "onCreate: SupportMapFragment não encontrado! Verifique o ID no layout.");
            // Você pode querer tratar esse erro de forma mais robusta,
            // talvez mostrando uma mensagem ao usuário ou finalizando a activity.
            Toast.makeText(this, "Erro ao carregar o fragmento do mapa.", Toast.LENGTH_LONG).show();
            return;
        }

        Log.d(TAG, "onCreate: Solicitando o mapa de forma assíncrona (getMapAsync)");
        mapFrag.getMapAsync((OnMapReadyCallback) this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i(TAG, "onMapReady: O mapa está pronto!"); // Log quando o mapa estiver pronto
        Toast.makeText(this, "Mapa carregado com sucesso!", Toast.LENGTH_SHORT).show();
        this.map = googleMap;

        Log.d(TAG, "onMapReady: Adicionando marcadores...");
        map.addMarker(new MarkerOptions().position(minhaCasaTO).title("Minha Casa TO"));
        map.addMarker(new MarkerOptions().position(minhaCasaVC).title("Minha Casa VC"));
        map.addMarker(new MarkerOptions().position(dpiUFV).title("DPI UFV"));
        Log.d(TAG, "onMapReady: Marcadores adicionados.");

        Intent it = getIntent();
        String option = it.getStringExtra("option");
        if(option.equals("Minha casas em Teofilo Otoni")){
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(minhaCasaTO, 14));
        }
        if(option.equals("Minha casa em Viçosa")){
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(minhaCasaVC, 14));
        }
        if(option.equals("DPI UFV")){
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(dpiUFV, 14));
        }
    }

}