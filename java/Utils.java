package com.example.fechanactarot;

import android.content.Context;

public final class Utils {
    public static final String LOG_TAG = Utils.class.getSimpleName();


    public static Arcano BuscarArcano(Context contexto, Integer nroArcano) {

        ArcanoDBAccess databaseaccess = ArcanoDBAccess.getInstance(contexto);
        databaseaccess.open();
        Arcano arcanoActual = databaseaccess.getArcano(nroArcano);
        databaseaccess.close();
        return  arcanoActual;
    }

    public static NumeroEspecial BuscarNumeroEspecial(Context contexto, Integer nroEspecial) {

        NumeroEspecialDBAccess databaseaccess = NumeroEspecialDBAccess.getInstance(contexto);
        databaseaccess.open();
        NumeroEspecial numeroActual = databaseaccess.getNumeroEspecial(nroEspecial);
        databaseaccess.close();
        return  numeroActual;
    }

    public static Integer ResolverImagenDesdeNombre (Context contexto, String imagen, Integer mazoINT) {
        String uri = "@drawable/" + imagen;
        if (mazoINT==1) uri= uri + "m";
        int imageResource = contexto.getResources().getIdentifier(uri, null, contexto.getPackageName());
        //Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        return imageResource;
    }

    /**
     * Devuelvo un onjeto {@link Arcano} parseando la informacion del
     * primer terremoto que viene en el input
     */
    /*
    private static Arcano extraigoDeJSON(String arcanoJSON) {
        // Si el JSON esta vacio devuelvo null
        if (TextUtils.isEmpty(arcanoJSON)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(arcanoJSON);
            JSONArray featureArray = baseJsonResponse.getJSONArray("cards");

            // Si hay resultados en el array de Features
            if (featureArray.length() > 0) {
                // Extraigo el Arcano
                JSONObject firstarcano = featureArray.getJSONObject(0);

                // Extraigo los datos
                String nombre = firstarcano.getString("name");
                String imagen = firstarcano.getString("name_short");
                String descDerecho = firstarcano.getString("meaning_up");
                String descReves = firstarcano.getString("meaning_rev");
                String desc = firstarcano.getString("desc");


                // Creo un objeto {@link Evento}
                return new Arcano(1,nombre,imagen, descDerecho, descReves, desc);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problemas parseando El JSON", e);
        }
        return null;
    }

    public static boolean ChequeoConexionIntenet(Context contexto) {
        Boolean conectado=false;

        ConnectivityManager connectivityManager = (ConnectivityManager) contexto.getSystemService(contexto.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            conectado = true;
        } else {
            Toast.makeText(contexto.getApplicationContext(), R.string.no_internet_available, Toast.LENGTH_LONG).show();
        }
        return  conectado;
    }

     */
}