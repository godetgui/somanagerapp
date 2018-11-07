package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import fr.eseo.dis.godetgui.somanagerapp.data.JM;
import fr.eseo.dis.godetgui.somanagerapp.data.SomanagerDatabase;
import fr.eseo.dis.godetgui.somanagerapp.threads.FetchLogon;
import fr.eseo.dis.godetgui.somanagerapp.threads.FetchRole;

public class ConnectionActivity extends AppCompatActivity {

    private Button btnConnexion;
    private String status;
    private EditText login;
    private String token;
    private String userConnected;
    private Context context;
    SharedPreferences myPrefs;
    SharedPreferences.Editor editor;
    SomanagerDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        this.context = this.getApplicationContext();
        //Récupération de la db
        this.db = SomanagerDatabase.getDatabase(this.context);
        //this.db.clearAllTables(); //UNIQUEMENT POUR LE DEV !!!!!!!!!!!!!!!!!


        this.myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.editor = myPrefs.edit();

    }


    public void onClickBtn(View view){
        this.login = (EditText)findViewById(R.id.loginText);
        EditText password = (EditText)findViewById(R.id.passwordText);

        FetchLogon fetchDataLogon = new FetchLogon(this,this.login.getText().toString(), password.getText().toString());
        fetchDataLogon.execute();

    }



    /**
     * Récupération des données issues du webService LOGON
     * @param JO
     * @throws JSONException
     */
    //Impossibilité de récupérer le context de l'activity dans cette méthode ????
    public void getDataLogon(JSONObject JO, String user) throws JSONException {
        System.out.println("********************RESULTAT JSON LOGON: "+JO);
        String result = JO.getString("result");

        if (result.equals("OK") ){
            this.userConnected = user;
            this.token = JO.getString("token");
            editor.putString("USERNAME", this.login.getText().toString());
            editor.putString("TOKEN", this.token);
            editor.commit();

            this.fetchRole();

        }
        else {
            String error = JO.getString("error");
            createDialogAlert();
        }

    }
    //Impossibilité de récupérer le context de l'activity dans cette méthode ????
    public void fetchRole(){
        FetchRole fetchRole = new FetchRole(this,userConnected, token);
        fetchRole.execute();
    }

    public void getDataRole(JSONObject JO) throws JSONException {
        JSONArray response = JO.getJSONArray("info");
        JSONObject infos = response.getJSONObject(0);
        String description = infos.getString("descr");
        String username = infos.getString("username");
        String forename = infos.getString("forename");
        String surname = infos.getString("surname");




        if (description.equals("Professeur")){
            this.db.jmDao().insertJm(new JM(0, surname, forename, username));
            Intent goToJurysJMActivity = new Intent(ConnectionActivity.this,JurysJMActivity.class);
            startActivity(goToJurysJMActivity);

        } else if (description.equals("Service Communications")){
            Intent goToJAllProjectsComm = new Intent(ConnectionActivity.this,CommAllProjectsActivity.class);
            startActivity(goToJAllProjectsComm);
        }

    }


    public void createDialogAlert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ConnectionActivity.this);

        alertDialogBuilder
                .setTitle("Error Connection")
                .setMessage("Bad credentials")
                .setCancelable( false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }



    }