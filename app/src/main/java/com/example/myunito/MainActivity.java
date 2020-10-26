package com.example.myunito;

import java.util.Arrays;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    //dichiarazione delle variabili
    EditText generalita, matricola;
    Button login;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //input dati utente
        generalita = (EditText) findViewById(R.id.generalita);
        matricola = (EditText) findViewById(R.id.matricola);

        login = (Button) findViewById(R.id.login);

        //al click del tasto login entra nel metodo
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nome = generalita.getText().toString().trim();
                String numero = matricola.getText().toString().trim();

                //simulo un database con gli utenti, in realtà è un array
                String[] utentiInDatabase = {"matteo belletti", "sergio rossi", "gabriele conti", "riccardo toma", "asya mantovani", "federica garrone"};
                String[] matricoleInDatabase = {"839730", "876321", "987354", "756238", "989567", "456827"};


                //controllo se il nome, il numero della matricola è vuoto
                //e se l'utente è negli array creati in precedenza per simulare un database
                if(nome.isEmpty() && numero.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Il campo nome e matricola sono obbligatori",Toast.LENGTH_LONG).show();
                }
                else if(nome.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Il campo nome è obbligatorio obbligatorio",Toast.LENGTH_LONG).show();
                }
                else if(numero.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Il campo matricola è obbligatorio",Toast.LENGTH_LONG).show();
                }
                else if(!Arrays.asList(utentiInDatabase).contains(nome.toLowerCase()) && !Arrays.asList(matricoleInDatabase).contains(numero))
                {
                    Toast.makeText(MainActivity.this,"L'utente con nome " + nome + " e matricola " + numero + " non è iscritto all'università",Toast.LENGTH_LONG).show();
                }
                else if(!Arrays.asList(utentiInDatabase).contains(nome.toLowerCase()))
                {
                    Toast.makeText(MainActivity.this,"L'utente con nome " + nome + " non è iscritto all'università",Toast.LENGTH_LONG).show();
                }
                else if(!Arrays.asList(matricoleInDatabase).contains(numero))
                {
                    Toast.makeText(MainActivity.this,"L'utente con matricola " + numero + " non è iscritto all'università",Toast.LENGTH_LONG).show();
                }
                else
                {
                    //passaggio al nuovo intento
                    in = new Intent(MainActivity.this, CalendarMain.class);

                    //passaggio del nome e del numero di matricola al nuovo intento
                    in.putExtra("nome",nome);
                    in.putExtra("numero",numero);

                    //messaggio di login eseguito
                    Toast.makeText(MainActivity.this,"Ciao " + nome + ", hai effettuato l'accesso con successo!",Toast.LENGTH_LONG).show();

                    startActivity(in);
                }
            }
        });
    }
}
