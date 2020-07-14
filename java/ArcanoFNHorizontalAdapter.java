package com.example.fechanactarot;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class ArcanoFNHorizontalAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ItemFechaNacimiento> itemsFNArrayList;
    private Integer mazoInt;

    public ArcanoFNHorizontalAdapter(Context context, ArrayList<ItemFechaNacimiento> itemsFNArrayList, Integer mazo) {
        this.context = context;
        this.itemsFNArrayList = itemsFNArrayList;
        this.mazoInt=mazo;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return itemsFNArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsFNArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fila_arcano, null, true);

            holder.tipoDatoTV = (TextView) convertView.findViewById(R.id.tipoDatoTV);
            holder.nroDirectoTV = (TextView) convertView.findViewById(R.id.numeroDirectoTV);
            holder.nroReducidoTV      = (TextView) convertView.findViewById(R.id.numeroReducidoTV);
            holder.arcanoNombreTV      = (TextView) convertView.findViewById(R.id.arcanoNombreTV);
            holder.arcanoDescricionTV = (TextView) convertView.findViewById(R.id.arcanoDescripcionTV);
            holder.arcanoImagenIV =  (ImageView) convertView.findViewById(R.id.arcanoImagenIV);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tipoDatoTV.setText(itemsFNArrayList.get(position).getMyTipoDato());
        holder.nroDirectoTV.setText(Integer.toString(itemsFNArrayList.get(position).getMyNumeroDirecto()));
        holder.nroReducidoTV.setText(Integer.toString(itemsFNArrayList.get(position).getMyNumeroReducido()));

        if (NumeroEspecial.esNumeroKararmico(itemsFNArrayList.get(position).getMyNumeroDirecto())) {
            holder.nroDirectoTV.setTextColor(Color.parseColor("#ff0000"));
        } else if (NumeroEspecial.esNumeroMaestro(itemsFNArrayList.get(position).getMyNumeroDirecto())) {
            holder.nroDirectoTV.setTextColor(Color.parseColor("#39FF14"));
        }

        if (NumeroEspecial.esNumeroKararmico(itemsFNArrayList.get(position).getMyNumeroReducido())) {
            holder.nroReducidoTV.setTextColor(Color.parseColor("#ff0000"));
        } else if (NumeroEspecial.esNumeroMaestro(itemsFNArrayList.get(position).getMyNumeroReducido())) {
            holder.nroReducidoTV.setTextColor(Color.parseColor("#39FF14"));
        }

        holder.arcanoNombreTV.setText(itemsFNArrayList.get(position).getMyArcano().getMyNombre());

        Integer imagenINT = Utils.ResolverImagenDesdeNombre(context, itemsFNArrayList.get(position).getMyArcano().getMyImagen(),mazoInt);
        holder.arcanoImagenIV.setImageResource(imagenINT);

        //holder.arcanoDescricionTV.setText(itemsFNArrayList.get(position).getMyArcano().getMyDescripcion());

        if (itemsFNArrayList.get(position).getMyInvertido()) {
            holder.arcanoImagenIV.setRotation(180F);
        }
        return convertView;
    }

    private class ViewHolder {
        protected TextView tipoDatoTV ;
        protected TextView nroDirectoTV ;
        protected TextView nroReducidoTV;
        protected TextView arcanoNombreTV;
        protected TextView arcanoDescricionTV;
        private ImageView arcanoImagenIV ;

    }

}
