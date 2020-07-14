package com.example.fechanactarot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FechaNacimientoActivity extends AppCompatActivity {
    static final String C_DIA = "valor_dia";
    static final String C_MES = "valor_mes";
    static final String C_ANIO = "valor_anio";
    static final String C_ARCANO = "valor_arcano";

    Integer mazoINT;
    Integer diaINT;
    Integer mesINT;
    Integer anioINT;

    TextView diaTV;
    TextView mesTV;
    TextView anioTV;

    HorizontalListView arcanosHLV;
    TextView edadEventoTV;
    TextView anioEventoTV;
    TextView cicloEventoTV;
    TextView nroDestinoAnteriorTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fecha_nacimiento);

        CargarPreferencias();

        diaTV = findViewById(R.id.diaTV);
        mesTV = findViewById(R.id.mesTV);
        anioTV = findViewById(R.id.anioTV);

        Bundle datos = this.getIntent().getExtras();
        diaTV.setText(datos.getString(C_DIA));
        diaINT = Integer.parseInt(datos.getString(C_DIA));
        mesTV.setText(datos.getString(C_MES));
        mesINT = Integer.parseInt(datos.getString(C_MES));
        anioTV.setText(datos.getString(C_ANIO));
        anioINT = Integer.parseInt(datos.getString(C_ANIO));

        String font_path = "fonts/Orange Blossom.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(), font_path);
        TextView textoFecha = findViewById(R.id.textofecha);
        TextView textobar1 = findViewById(R.id.textobar1);
        TextView textobar2 = findViewById(R.id.textobar2);
        textoFecha.setTypeface(TF);
        diaTV.setTypeface(TF);
        textobar1.setTypeface(TF);
        mesTV.setTypeface(TF);
        textobar2.setTypeface(TF);
        anioTV.setTypeface(TF);

        arcanosHLV = findViewById(R.id.arcanosHLV);
        CargarLisView();

        edadEventoTV = findViewById(R.id.edadEventoTV);
        anioEventoTV = findViewById(R.id.anioEventoTV);
        cicloEventoTV = findViewById(R.id.cicloEventoTV);
        nroDestinoAnteriorTV = findViewById(R.id.nroDestinoAnteriorTV);

        CalcularDatosEvento();
    }

    private void CargarPreferencias() {
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        String mazo = preferencias.getString(getString(R.string.settings_deck_key),"0");
        mazoINT = Integer.parseInt(mazo);
    }

    private void CalcularDatosEvento() {
        edadEventoTV.setText(Integer.toString(diaINT + mesINT));
        anioEventoTV.setText(Integer.toString(diaINT + mesINT + anioINT));
        cicloEventoTV.setText(Integer.toString(SumarDigitos(diaINT + mesINT)));
        if (SumarDigitos(diaINT + mesINT + anioINT) == SumarDigitos(SumarDigitos(diaINT) + SumarDigitos(mesINT) + SumarDigitos(anioINT))) {
            nroDestinoAnteriorTV.setText(R.string.yes);
        } else nroDestinoAnteriorTV.setText(R.string.no);
    }

    private void CargarLisView() {
        ArrayList<ItemFechaNacimiento> listaItemsFN = new ArrayList<>();

        listaItemsFN.add(CargarDia());
        listaItemsFN.add(CargarMes());
        listaItemsFN.add(CargarAnio());
        listaItemsFN.add(CargarNroDestino());
        listaItemsFN.add(CargarTarea());

        final ArcanoFNHorizontalAdapter adaptadorDeListaItemsFN = new ArcanoFNHorizontalAdapter(this, listaItemsFN,mazoINT);

        arcanosHLV.setAdapter(adaptadorDeListaItemsFN);

        arcanosHLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ItemFechaNacimiento itemFNActual = (ItemFechaNacimiento) adaptadorDeListaItemsFN.getItem(position);
                Intent abrirDetalleArcanoFN = new Intent(getApplicationContext(), DetalleArcanoFNActivity.class);
                abrirDetalleArcanoFN.putExtra(C_ARCANO, itemFNActual);
                startActivity(abrirDetalleArcanoFN);
            }
        });
    }

    private Integer SumarDigitos(Integer numero) {
        Integer suma = 0;
        Integer digito = 0;

        while (numero != 0) {
            digito = numero % 10;
            numero = numero / 10;
            suma = suma + digito;
        }
        return suma;
    }

    private ItemFechaNacimiento CargarDia() {
        Integer nroDirecto = 0;
        Integer nroReducido = 0;

        if (diaINT > 22) {
            nroDirecto = SumarDigitos(diaINT);
        } else nroDirecto = diaINT;

        nroReducido = nroDirecto;

        Arcano arcanoActual = Utils.BuscarArcano(getApplicationContext(),nroReducido);

        ItemFechaNacimiento itemActual = new ItemFechaNacimiento(getString(R.string.FN_Day),
                getString(R.string.FN_Day_Descripcion), nroDirecto, nroReducido, false,
                arcanoActual);

        return itemActual;
    }

    private ItemFechaNacimiento CargarMes() {
        Integer nroDirecto = 0;
        Integer nroReducido = 0;
        String arcanoNom = "";
        String arcanoDesc = "";

        nroDirecto = mesINT;
        nroReducido = mesINT;

        Arcano arcanoActual = Utils.BuscarArcano(getApplicationContext(),nroReducido);

        ItemFechaNacimiento itemActual = new ItemFechaNacimiento(getString(R.string.FN_Month),
                getString(R.string.FN_Month_Description), nroDirecto, nroReducido, true,
                arcanoActual);

        return itemActual;
    }

    private ItemFechaNacimiento CargarAnio() {
        Integer nroDirecto = 0;
        Integer nroReducido = 0;
        String arcanoNom = "";
        String arcanoDesc = "";

        nroDirecto = SumarDigitos(anioINT);

        if (nroDirecto > 22) {
            nroReducido = SumarDigitos(nroDirecto);
        } else nroReducido = nroDirecto;

        Arcano arcanoActual = Utils.BuscarArcano(getApplicationContext(),nroReducido);

        ItemFechaNacimiento itemActual = new ItemFechaNacimiento(getString(R.string.FN_Year),
                getString(R.string.FN_Year_Description), nroDirecto, nroReducido, false,
                arcanoActual);

        return itemActual;

    }

    private ItemFechaNacimiento CargarNroDestino() {
        Integer nroDirecto = 0;
        Integer nroReducido = 0;
        String arcanoNom = "";
        String arcanoDesc = "";

        nroDirecto = SumarDigitos(diaINT) + SumarDigitos(mesINT) + SumarDigitos(anioINT);

        if (nroDirecto > 10) {
            nroReducido = SumarDigitos(nroDirecto);
        } else nroReducido = nroDirecto;

        Arcano arcanoActual = Utils.BuscarArcano(getApplicationContext(),nroReducido);

        ItemFechaNacimiento itemActual = new ItemFechaNacimiento(getString(R.string.FN_Destiny),
                getString(R.string.FN_Destiny_Description), nroDirecto, nroReducido, false,
                arcanoActual);

        return itemActual;
    }

    private ItemFechaNacimiento CargarTarea() {
        Integer nroDirecto = 0;
        Integer nroReducido = 0;
        String arcanoNom = "";
        String arcanoDesc = "";

        nroDirecto = SumarDigitos(mesINT + anioINT);

        if (nroDirecto > 22) {
            nroReducido = SumarDigitos(nroDirecto);
        } else nroReducido = nroDirecto;

        Arcano arcanoActual = Utils.BuscarArcano(getApplicationContext(),nroReducido);

        ItemFechaNacimiento itemActual = new ItemFechaNacimiento(getString(R.string.FN_Task),
                getString(R.string.FN_Task_Description), nroDirecto, nroReducido, false,
                arcanoActual);

        return itemActual;
    }
}
