package com.cev.blablapub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText nombre;
    EditText apellidos;
    EditText edad;
    EditText email;
    RadioGroup sexo;
    RadioButton hombre;
    RadioButton mujer;
    CheckBox siHaAceptadoPoliticaUso;
    Button registrarme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        email = findViewById(R.id.email);
        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellido);
        edad = findViewById(R.id.edad);
        sexo = findViewById(R.id.sexo);
        siHaAceptadoPoliticaUso = findViewById(R.id.aceptarCondiciones);
        registrarme = findViewById(R.id.registrar);
    }
    public void inscribirse(View vista) {
        Toast toastKO = Toast.makeText(this, "Faltan datos. Por favor complete la información solicitada",
                Toast.LENGTH_LONG);
        Toast toastOK = Toast.makeText(this, "Gracias " + nombre.getText() + " ¡La inscripción se ha hecho con éxito!",
                Toast.LENGTH_LONG);

        Toast toastMenorEdad = Toast.makeText(this, "Los participantes deben ser mayores de edad",
                Toast.LENGTH_LONG);

        int edadConcursante = 0;
        boolean edadOK = false;
        if (edad.getText().length() > 0) {
            edadConcursante = Integer.parseInt(edad.getText().toString());
        }
        if (edadConcursante >= 18) {
            edadOK = true;
        }

        boolean todoOK = false;
        boolean todoDatosOK = false;

        todoDatosOK = (nombre.getText().length() > 0) && (apellidos.getText().length() > 0) && (edad.getText().length() > 0)
                && //(hombre.isChecked() || mujer.isChecked()) &&
                ((siHaAceptadoPoliticaUso.isChecked()) ||
                        (siHaAceptadoPoliticaUso.isChecked() == false));

        todoOK = todoDatosOK && edadOK;
        //Si tod o bien guardar
        if (todoOK) {
             toastOK.show();
        }
        if (!todoDatosOK) {
            toastKO.show();
        }
        if (todoDatosOK && !edadOK) {
            toastMenorEdad.show();
        }
        if(todoOK == true){
            PetitionsPost petitionsPost = new PetitionsPost(this);
            String url = "http://127.0.0.1:8000/api/user";
            String name = nombre.toString()+ apellidos.toString();
            petitionsPost.execute(url,"dd","ff@ff","1234","22","hombre");



        }

    }
}