package com.example.blablapub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public abstract class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Button login;
    Button registro;
    Button recovery;
    ImageView imagenUser;

    LinearLayout vistaLogin;
    EditText loginEmail;
    EditText loginPassword;
    Button loginButton;

    LinearLayout vistaRegistro;
    EditText nombre;
    EditText apellidos;
    EditText edad;
    RadioGroup sexo;
    RadioButton hombre;
    RadioButton mujer;
    CheckBox siHaAceptadoPoliticaUso;
    Button registrarme;

    ListaBares listaBares = new ListaBares();

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        registro = findViewById(R.id.registro);
        recovery = findViewById(R.id.recovery);
        imagenUser = findViewById(R.id.imagenApp);

        vistaLogin = findViewById(R.id.vistaLogin);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);

        vistaRegistro = findViewById(R.id.vistaRegistro);
        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        edad = findViewById(R.id.edad);
        sexo = findViewById(R.id.sexo);
        siHaAceptadoPoliticaUso = findViewById(R.id.aceptarCondiciones);
        registrarme = findViewById(R.id.registrarse);

        siHaAceptadoPoliticaUso.setOnCheckedChangeListener(this);
    }

    public void abrirLogin(View vista) {
        login.setVisibility(View.GONE);
        registro.setVisibility(View.GONE);

        vistaLogin.setVisibility(View.VISIBLE);

        loginEmail.setText("");
        loginPassword.setText("");
    }

    public void backToMain(View vista) {
        login.setVisibility(View.VISIBLE);
        registro.setVisibility(View.VISIBLE);

        vistaLogin.setVisibility(View.GONE);
    }

    /*
    Oculta la presentación y visualiza los campos de entrada de datos
     */
    public void abrirRegistro(View vista) {
        login.setVisibility(View.GONE);
        registro.setVisibility(View.GONE);

        vistaRegistro.setVisibility(View.VISIBLE);

        nombre.setText("");
        apellidos.setText("");
        edad.setText("");
        sexo.clearCheck();

        siHaAceptadoPoliticaUso.setChecked(false);
    }

    /*
    Oculta los campos de datos y visualiza la presentación
     */
    public void ocultaRegistro(View vista) {
        imagenUser.setVisibility(View.VISIBLE);
        registro.setVisibility(View.VISIBLE);

        vistaRegistro.setVisibility(View.GONE);
    }


    /*
    Comprueba si todos los datos necesarios se han rellenado
    y si es así muestra el Toast correspondiente y oculta los campos de datos
    y visualiza la presntación para permitir otro registro
     */
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
                && (hombre.isChecked() || mujer.isChecked()) &&
                ((siHaAceptadoPoliticaUso.isChecked()) ||
                        (siHaAceptadoPoliticaUso.isChecked() == false));

        todoOK = todoDatosOK && edadOK;
        //Si tod o bien guardar
        if (todoOK) {
            toastOK.show();
            ocultaRegistro(vista);
        }
        if (!todoDatosOK) {
            toastKO.show();
        }
        if (todoDatosOK && !edadOK) {
            toastMenorEdad.show();
        }

    }

    public void verLista(View view) {

        //Recuperamos la lista guardada con anterioridad
        String jsonListaBares = sharedPreferences.getString("listaBares", "");
        if (!jsonListaBares.isEmpty()) {
            listaBares = listaBares.fromJSON(jsonListaBares);
        }
    }
}