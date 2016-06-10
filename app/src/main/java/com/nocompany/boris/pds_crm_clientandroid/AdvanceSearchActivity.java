package com.nocompany.boris.pds_crm_clientandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdvanceSearchActivity extends AppCompatActivity {

    EditText editTextRun;
    EditText editTextNombre;
    EditText editTextApellido;
    EditText editTextEmail;
    EditText editTextFono;
    EditText editTextDireccion;
    EditText editTextGenero;


    TextView textViewResult;
    Button buttonBuscarAdvance;

    String IpWS = "192.168.20.38"; //ej 192.168.0.1
    String NAMESPACE = "http://service";
    String URL = "http://"+IpWS+":8080/PDS-CRM/services/ServiceDAO?wsdl";
    String SOAP_ACTION = "http://"+IpWS+":8080/PDS-CRM/services/ServiceDAO";
    String METHOD_NAME = "busquedaAvanzada"; //busquedaAvanzadaReturn array

    String strRun;
    String strNombre;
    String strApellido;
    String strEmail;
    String strFono;
    String strDireccion;
    String strGenero;

    String result;
    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_search);

        editTextRun = (EditText) findViewById(R.id.editTextFieldRun);
        editTextNombre = (EditText) findViewById(R.id.editTextFieldNombre);
        editTextApellido = (EditText) findViewById(R.id.editTextFieldApellido);
        editTextEmail = (EditText) findViewById(R.id.editTextFieldEmail);
        editTextFono = (EditText) findViewById(R.id.editTextFieldFono);
        editTextDireccion = (EditText) findViewById(R.id.editTextFieldDireccion);
        editTextGenero = (EditText) findViewById(R.id.editTextFieldGenero);

        textViewResult = (TextView) findViewById(R.id.textViewRAWResult);
        buttonBuscarAdvance = (Button) findViewById(R.id.buttonBuscarAvanzado);

        buttonBuscarAdvance.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!editTextRun.getText().toString().equals("") && editTextRun.getText().length() != 0){
                    strRun = editTextRun.getText().toString();
                }else{
                    strRun = "";
                }

                if(!editTextNombre.getText().toString().equals("") && editTextNombre.getText().length() != 0){
                    strNombre = editTextNombre.getText().toString();
                }else{
                    strNombre = "";
                }

                if(!editTextApellido.getText().toString().equals("") && editTextApellido.getText().length() != 0){
                    strApellido = editTextApellido.getText().toString();
                }else{
                    strApellido = "";
                }

                if(!editTextEmail.getText().toString().equals("") && editTextEmail.getText().length() != 0){
                    strEmail = editTextEmail.getText().toString();
                }else{
                    strEmail = "";
                }

                if(!editTextFono.getText().toString().equals("") && editTextFono.getText().length() != 0){
                    strFono = editTextFono.getText().toString();
                }else{
                    strFono = "";
                }

                if(!editTextDireccion.getText().toString().equals("") && editTextDireccion.getText().length() != 0){
                    strDireccion = editTextDireccion.getText().toString();
                }else{
                    strDireccion = "";
                }

                if(!editTextGenero.getText().toString().equals("") && editTextGenero.getText().length() != 0){
                    strGenero = editTextGenero.getText().toString();
                }else{
                    strGenero = "";
                }

                //generateAsyncTask task = new generateAsyncTask();
                //task.execute();
            }
        });
    }


}
