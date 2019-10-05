package com.example.imc_peso_ideal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {


    TextView res;
    EditText peso,estatura;
    Button btncalcular,btnreset;
    RadioButton fem,mas;


    View.OnClickListener calcularListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickCalcular();
        }
    };

    View.OnClickListener resetListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickReset();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mas = (RadioButton) findViewById(R.id.radBtnMasculino);
        fem = (RadioButton) findViewById(R.id.radBtnFemenino);
        res = (TextView) findViewById(R.id.tvResultado);
        peso = (EditText) findViewById(R.id.txtPeso);
        estatura = (EditText) findViewById(R.id.txtEstatura);
        btncalcular = (Button) findViewById(R.id.btnCalcular);
        btncalcular.setOnClickListener(calcularListener);
        btnreset = (Button) findViewById(R.id.btnReset);
        btnreset.setOnClickListener(resetListener);

    }



    public void clickCalcular() {
        //operaciones para calcular IMC
        float Peso = Float.parseFloat(peso.getText().toString());
        float Estatura = Float.parseFloat(estatura.getText().toString());
        double imc = Peso/(Estatura*Estatura);

        if ((peso.getText().toString().length()==0)||(estatura.getText().toString().length()==0)) {

            Toast men = Toast.makeText(this,
                    "ERROR, Dejaste un campo vacio", Toast.LENGTH_LONG);
            men.show();

        }else {

            double f1, f2;
            double pid;
            String msj;

            //RadioButton Femenino
            if (fem.isChecked()) {
                f1 = 2.25;
                f2 = 45;
                Estatura = Estatura * 100;
                pid = ((((Estatura - 152.4) / 2.54) * f1) + f2);
                res.setText("Tu índice es: " + imc + "\n" + "Tu peso ideal es: " + pid);
            } else {
                //RadioButton Masculino
                f1 = 2.7;
                f2 = 47.75;
                Estatura = Estatura * 100;
                pid = ((((Estatura - 152.4) / 2.54) * f1) + f2);
                res.setText("Tu índice es: " + imc + "\n" + "Tu peso ideal es: " + pid);
            }
            if (imc < 16.00) {
                msj = "Infrapeso: Delgadez Severa";
            } else if (imc < 17) {
                msj = "Infrapeso: Delgadez moderada";
            } else if (imc < 18.5) {
                msj = "Infrapeso: Delgadez aceptable";
            } else if (imc < 24.5) {
                msj = "Peso Normal";
            } else if (imc < 30) {
                msj = "Sobrepeso";
            } else if (imc < 35) {
                msj = "Obeso: Tipo I";
            } else if (imc < 40) {
                msj = "Obeso: Tipo II";
            } else if (imc >= 40.00) {
                msj = "Obeso: Tipo III";
            } else {
                msj = "Inténtalo nuevamente";
            }
            //Alerta para mostrar resultados
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(msj)
                    .setTitle("Tu indice de masa corporal indica:")
                    .setCancelable(false)
                    .setNeutralButton("Aceptar",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            btncalcular.setEnabled(false);
            btnreset.setEnabled(true);
        }

    }


    public void clickReset(){
        btncalcular.setEnabled(true);
        btnreset.setEnabled(false);
        res.setText("");
        peso.setText("");
        estatura.setText("");
        peso.requestFocus();
    }

 }


