package com.example.fechanactarot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleArcanoFNActivity extends AppCompatActivity {
    static final String C_ARCANO = "valor_arcano";

    Integer mazoINT;
    TextView tareaPpalTV;
    TextView arcanoPpalTV;
    ImageView arcanoPpalIG;
    TextView tareaNombreTV;
    TextView tareaDescTV;
    TextView nroDirectoTV;
    TextView nroReducidoTV;
    TextView nroMaestroKarmicoTV;
    TextView nroMaestroKarmicoDescTV;
    TextView arcanoNombreTV;
    TextView arcanoDescCortaTV;
    TextView arcanoDescLargaTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_arcanofn);

        CargarPreferencias();

        Bundle datos = this.getIntent().getExtras();
        ItemFechaNacimiento itemActual = (ItemFechaNacimiento) datos.getSerializable(C_ARCANO);
        //ItemFechaNacimiento itemActual = (ItemFechaNacimiento) getIntent().getSerializableExtra(C_ARCANO);

        tareaPpalTV = findViewById(R.id.tareaPpalTV);
        arcanoPpalTV = findViewById(R.id.arcanoPpalTV);
        arcanoPpalIG = findViewById(R.id.arcanoPpalIG);
        tareaNombreTV = findViewById(R.id.tareaNombreTV);
        tareaDescTV = findViewById(R.id.tareaDescTV);
        nroDirectoTV = findViewById(R.id.nroDirectoTV);
        nroReducidoTV = findViewById(R.id.nroReducidoTV);
        nroMaestroKarmicoTV = findViewById(R.id.nroMaestroKarmicoTV);
        nroMaestroKarmicoDescTV = findViewById(R.id.nroMaestroKarmicoDescTV);
        arcanoNombreTV = findViewById(R.id.arcanoNombreTV);
        arcanoDescCortaTV = findViewById(R.id.arcanoDescCortaTV);
        arcanoDescLargaTV = findViewById(R.id.arcanoDescLargaTV);

        updateUi(itemActual);

        String font_path = "fonts/Orange Blossom.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        tareaPpalTV.setTypeface(TF);
        arcanoPpalTV.setTypeface(TF);

    }

    private void CargarPreferencias() {
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        String mazo = preferencias.getString(getString(R.string.settings_deck_key),"0");
        mazoINT = Integer.parseInt(mazo);
    }

    private void updateUi(ItemFechaNacimiento itemActual) {
        tareaPpalTV.setText(itemActual.getMyTipoDato() + ": ");
        arcanoPpalTV.setText(itemActual.getMyArcano().getMyNombre());
        arcanoDescCortaTV.setText(itemActual.getMyArcano().getMyDescripcion());

        Integer imagenINT = Utils.ResolverImagenDesdeNombre(this, itemActual.getMyArcano().getMyImagen(),mazoINT);
        arcanoPpalIG.setImageResource(imagenINT);

        if (itemActual.getMyInvertido()) {
            arcanoPpalIG.setRotation(180F);
            arcanoDescLargaTV.setText(itemActual.getMyArcano().getMyDescripcionCortaReves());
        } else {
            arcanoDescLargaTV.setText(itemActual.getMyArcano().getMyDescripcionCortaDerecho());
        }

        tareaNombreTV.setText(itemActual.getMyTipoDato() + ": ");
        tareaDescTV.setText(itemActual.getMyTipoDatoDesc());

        nroDirectoTV.setText(getString(R.string.direct_number) + ": " + Integer.toString(itemActual.getMyNumeroDirecto()));

        nroReducidoTV.setText(getString(R.string.reduced_number) + ": " + Integer.toString(itemActual.getMyNumeroReducido()));

        arcanoNombreTV.setText(itemActual.getMyArcano().getMyNombre() + ": ");

        if (NumeroEspecial.tipoNumeroEspecial(itemActual.getMyNumeroDirecto())!="") cargarDatosNumueroEspecial(itemActual.getMyNumeroDirecto());
        else if (NumeroEspecial.tipoNumeroEspecial(itemActual.getMyNumeroReducido())!="") cargarDatosNumueroEspecial(itemActual.getMyNumeroReducido());
    }

    private void cargarDatosNumueroEspecial(Integer numero) {
        String descripcionTipo;
        String descripcionContra;
        NumeroEspecial numeroActual = Utils.BuscarNumeroEspecial(getApplicationContext(),numero);

        if (numeroActual.getMyTipo().equals(NumeroEspecial.C_KARMICO)) {
            descripcionTipo=getString(R.string.FN_KarmicNumber);
            descripcionContra=getString(R.string.tool);
        } else {
            descripcionTipo=getString(R.string.FN_MasterNumber);
            descripcionContra=getString(R.string.limiting);

        }

        nroMaestroKarmicoTV.setText(Integer.toString(numeroActual.getMyNumero()) + " - " + descripcionTipo + ": ");
        nroMaestroKarmicoDescTV.setText(numeroActual.getMyDescripcion() + " " + descripcionContra + getString(R.string.the_number)
                + " " + numeroActual.getMyContra() + "." );

    }
}
