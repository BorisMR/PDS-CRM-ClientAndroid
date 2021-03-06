package com.nocompany.boris.pds_crm_clientandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Clase encargada de capturar la cadena, conectarse al SOAP y efectuar la busqueda
 */
public class SimpleSearchActivity extends AppCompatActivity {

    EditText cadenaBusqueda;
    TextView textViewResult;
    Button buttonBuscarSimple;

    String IpWS = "192.168.20.38";
    String NAMESPACE = "http://query.addProperty(propInf);service";
    String URL = "http://"+IpWS+":8080/PDS-CRM/services/ServiceDAO?wsdl";
    String SOAP_ACTION = "http://"+IpWS+":8080/PDS-CRM/services/ServiceDAO";
    String METHOD_NAME = "busquedaSimple";

    String cadenaBusquedaStr;
    String result;
    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_search);

        cadenaBusqueda = (EditText) findViewById(R.id.cadenaBusqueda);
        textViewResult = (TextView) findViewById(R.id.textViewRAWResult);
        buttonBuscarSimple = (Button) findViewById(R.id.buttonBuscarSimple);

        buttonBuscarSimple.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!cadenaBusqueda.getText().toString().equals("") && cadenaBusqueda.getText().length() != 0){
                    cadenaBusquedaStr = cadenaBusqueda.getText().toString();
                    generateAsyncTask task = new generateAsyncTask();
                    task.execute();
                }else{
                    textViewResult.setText("Cadena de Busqueda");
                }
            }
        });
    }

    /**
     * Se comunica con el Servicio y le entrega la cadena de busqueda
     *
     * @param cadenaBusquedaStr
     */
    public void getResult(String cadenaBusquedaStr){
        SoapObject query = new SoapObject(NAMESPACE, METHOD_NAME);
        PropertyInfo propInf = new PropertyInfo();
        propInf.setName("cadenaBusqueda");
        propInf.setValue(cadenaBusquedaStr);
        propInf.setType(String.class);

        query.addProperty(propInf);

        SoapSerializationEnvelope toSend = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        toSend.dotNet = true;

        toSend.setOutputSoapObject(query);

        HttpTransportSE transportHttp = new HttpTransportSE(URL);

        try{
            transportHttp.call(SOAP_ACTION, toSend);

            SoapPrimitive response = (SoapPrimitive) toSend.getResponse();

            result = response.toString();//jsonformat
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class generateAsyncTask extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... params){
            Log.i(TAG, "doInBackground");
            getResult(cadenaBusquedaStr);
            return null;
        }

        @Override
        protected void onPostExecute(Void results){
            Log.i(TAG, "onPostExecute");
            //textViewResult.setText(result);
            try{
                JSONArray jsonArray = new JSONArray(result);
                String list = "";

                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);

                    list += "Run: " + object.getString("run") + "\n" +
                            "Nombre: " + object.get("nombre") + "\n" +
                            "Apellido: " + object.get("apellido") + "\n" +
                            "eMail: " + object.get("email") + "\n" +
                            "Fono: " + object.get("fono") + "\n" +
                            "Direccion: " + object.get("direccion") + "\n" +
                            "Genero: " + object.getString("genero") + "\n\n";
                }
                textViewResult.setText(list);
            }catch(JSONException ex){
                ex.printStackTrace();
            }
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
