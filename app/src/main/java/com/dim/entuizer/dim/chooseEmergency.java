package com.dim.entuizer.dim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class chooseEmergency extends AppCompatActivity {

    Double latitud, longitud;
    String Direccion;
    TextView txtLatitud, txtLongitud, txtDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_emergency);

        txtLatitud = (TextView) findViewById(R.id.txtLatitud);
        txtLongitud = (TextView) findViewById(R.id.txtLongitud);
        txtDireccion = (TextView) findViewById(R.id.txtDireccion);

        Bundle extras = getIntent().getExtras();
        latitud = extras.getDouble("latitud");
        longitud = extras.getDouble("longitud");
        Direccion = extras.getString("direccion");

        txtLatitud.setText(latitud.toString());
        txtLongitud.setText(longitud.toString());
        txtDireccion.setText(Direccion);
    }
}
