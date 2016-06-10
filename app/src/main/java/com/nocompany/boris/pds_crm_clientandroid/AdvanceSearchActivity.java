package com.nocompany.boris.pds_crm_clientandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

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

    String IpWS = "192.168.20.38";
    String NAMESPACE = "http://service";
    String URL = "http://"+IpWS+":8080/PDS-CRM/services/ServiceDAO?wsdl";
    String SOAP_ACTION = "http://"+IpWS+":8080/PDS-CRM/services/ServiceDAO";
    String METHOD_NAME = "busquedaAvanzada";

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

        textViewResult = (TextView) findViewById(R.id.textViewAdvanceRaw);
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
                generateAsyncTask task = new generateAsyncTask();
                task.execute();
            }
        });
    }

    /**
     * Metodo de busqueda y obtencion de resultados
     *
     * @param strRun
     * @param strNombre
     * @param strApellido
     * @param strEmail
     * @param strFono
     * @param strDireccion
     * @param strGenero
     */
    public void getResult(String strRun,
                          String strNombre,
                          String strApellido,
                          String strEmail,
                          String strFono,
                          String strDireccion,
                          String strGenero){

        SoapObject query = new SoapObject(NAMESPACE, METHOD_NAME);
        List<PropertyInfo> properties = new ArrayList<PropertyInfo>();
        PropertyInfo propInf = new PropertyInfo();

        propInf.setName("run");
        propInf.setValue(strRun);
        propInf.setType(String.class);
        properties.add(propInf);
        query.addProperty(propInf);

        propInf = new PropertyInfo();
        propInf.setName("nombre");
        propInf.setValue(strNombre);
        propInf.setType(String.class);
        properties.add(propInf);
        query.addProperty(propInf);

        propInf = new PropertyInfo();
        propInf.setName("apellido");
        propInf.setValue(strApellido);
        propInf.setType(String.class);
        properties.add(propInf);
        query.addProperty(propInf);

        propInf = new PropertyInfo();
        propInf.setName("email");
        propInf.setValue(strEmail);
        propInf.setType(String.class);
        properties.add(propInf);
        query.addProperty(propInf);

        propInf = new PropertyInfo();
        propInf.setName("fono");
        propInf.setValue(strFono);
        propInf.setType(String.class);
        properties.add(propInf);
        query.addProperty(propInf);

        propInf = new PropertyInfo();
        propInf.setName("direccion");
        propInf.setValue(strDireccion);
        propInf.setType(String.class);
        properties.add(propInf);
        query.addProperty(propInf);

        propInf = new PropertyInfo();
        propInf.setName("genero");
        propInf.setValue(strGenero);
        propInf.setType(String.class);
        properties.add(propInf);
        query.addProperty(propInf);

        SoapSerializationEnvelope toSend = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        toSend.dotNet = true;

        toSend.setOutputSoapObject(query);

        HttpTransportSE transportHttp = new HttpTransportSE(URL);

        try{
            transportHttp.call(SOAP_ACTION, toSend);

            SoapPrimitive response = (SoapPrimitive) toSend.getResponse();

            result = response.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Tarea asincrona encargada de llamar al metodo de busqueda
     */
    private class generateAsyncTask extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... params){
            Log.i(TAG, "doInBackground");
            getResult(strRun, strNombre, strApellido, strEmail, strFono, strDireccion, strGenero);
            return null;
        }

        @Override
        protected void onPostExecute(Void results){
            Log.i(TAG, "onPostExecute");
            textViewResult.setText(result);
        }

        @Override
        protected void onPreExecute(){
            Log.i(TAG, "onPreExecute");
            textViewResult.setText("Buscando");
        }

        @Override
        protected void  onProgressUpdate(Void... values){
            Log.i(TAG, "onProgressUpdate");
        }
    }

}
