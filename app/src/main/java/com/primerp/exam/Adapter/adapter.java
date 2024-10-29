package com.primerp.exam.Adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.primerp.exam.OtraActividad;
import com.primerp.exam.Class.Persona;
import com.primerp.exam.R;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    private List<Persona> listaPersonas;
    private Context context;
    private OnItemClickListener onItemClickListener;

    // Interfaz para manejar los clics
    public interface OnItemClickListener {
        void onItemClick(Persona persona);
    }

    public adapter(List<Persona> listaPersonas, Context context, OnItemClickListener onItemClickListener) {
        this.listaPersonas = listaPersonas;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {
        Persona persona = listaPersonas.get(position);
        holder.setData(persona);

        holder.layout.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(persona);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewName, textViewTelefono;
        ImageView img;
        ConstraintLayout layout;
        Persona persona;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Enlazar elementos de la vista
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewTelefono = itemView.findViewById(R.id.textViewTelefono);
            img = itemView.findViewById(R.id.img);
            layout = itemView.findViewById(R.id.layaou);

            // Configurar los eventos de clic
            img.setOnClickListener(this);
            textViewTelefono.setOnClickListener(this);
            textViewName.setOnClickListener(this);
        }

        public void setData(Persona persona) {
            this.persona = persona;
            textViewName.setText(persona.getNombre());
            textViewTelefono.setText(persona.getTelefono());
            img.setImageResource(persona.getImageResId());
            layout.setBackgroundColor(persona.getColor());
        }

        @Override
        public void onClick(View v) {
            // Identificar el clic según el elemento de vista
            if (v.getId() == R.id.textViewTelefono) {
                // Verificar permisos antes de hacer una llamada
                if (ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(v.getContext(), "Permiso para llamadas no concedido.", Toast.LENGTH_SHORT).show();
                } else {
                    // Realizar la llamada
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + persona.getTelefono()));
                    v.getContext().startActivity(intent);
                }
            } else if (v.getId() == R.id.textViewName) {
                // Navegar a OtraActividad con más información de la persona
                Intent intent = new Intent(v.getContext(), OtraActividad.class);
                intent.putExtra("nombre", persona.getNombre());
                intent.putExtra("telefono", persona.getTelefono());
                intent.putExtra("descripcion", persona.getDescription());
                intent.putExtra("imageResId", persona.getImageResId());
                intent.putExtra("color", persona.getColor());
                v.getContext().startActivity(intent);
            } else if (v.getId() == R.id.img) {
                // Verificar permisos antes de abrir la cámara
                if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(v.getContext(), "Permiso de cámara no concedido.", Toast.LENGTH_SHORT).show();
                } else {
                    // Abrir la cámara
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (cameraIntent.resolveActivity(v.getContext().getPackageManager()) != null) {
                        v.getContext().startActivity(cameraIntent);
                    } else {
                        // Mostrar un mensaje si no se encuentra una aplicación de cámara
                        Toast.makeText(v.getContext(), "No se encontró una aplicación de cámara", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
