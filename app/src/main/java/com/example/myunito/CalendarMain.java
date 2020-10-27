package com.example.myunito;

import java.util.Arrays;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class CalendarMain extends Activity
{
    //dichiarazione delle variabili
    Intent logout;

    //array con le lezioni prenotate da una matricola
    String lezioniPrenotateArray[] = {};
    String lezioniPrenotateArrayTMP[] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);

        //prendo i dati dall'intento precedente del login
        Intent intent = getIntent();
        final String nome = intent.getStringExtra("nome");
        final String numero = intent.getStringExtra("numero");

        //dichiarazione dei bottoni per ogni giorno della settimana
        final Button lunedi = findViewById(R.id.lunedi);
        final Button martedi = findViewById(R.id.martedi);
        final Button mercoledi = findViewById(R.id.mercoledi);
        final Button giovedi = findViewById(R.id.giovedi);
        final Button venerdi = findViewById(R.id.venerdi);

        //al click del bottone lunedi entra nel metodo
        lunedi.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v)
            {

                prenotaDisiscriviLezione("Lunedì", lunedi);

                //mostra i giorni delle lezioni prenotate
                mostraLezioniPrenotate();
            }
        });

        //al click del bottone martedi entra nel metodo
        martedi.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v)
            {

                prenotaDisiscriviLezione("Martedì", martedi);

                //mostra i giorni delle lezioni prenotate
                mostraLezioniPrenotate();
            }
        });

        //al click del bottone mercoledi entra nel metodo
        mercoledi.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v)
            {

                prenotaDisiscriviLezione("Mercoledì", mercoledi);

                //mostra i giorni delle lezioni prenotate
                mostraLezioniPrenotate();
            }
        });

        //al click del bottone giovedi entra nel metodo
        giovedi.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v)
            {

                prenotaDisiscriviLezione("Giovedì", giovedi);

                //mostra i giorni delle lezioni prenotate
                mostraLezioniPrenotate();
            }
        });

        //al click del bottone venerdi entra nel metodo
        venerdi.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v)
            {

                prenotaDisiscriviLezione("Venerdì", venerdi);

                Toast.makeText(CalendarMain.this,Arrays.toString(lezioniPrenotateArray),Toast.LENGTH_LONG).show();

                //mostra i giorni delle lezioni prenotate
                mostraLezioniPrenotate();
            }
        });

        //mostra i giorni delle lezioni prenotate
        mostraLezioniPrenotate();
    }


    //se la l'array lezioniPrenotateArray è vuoto mostra un messaggio che non è stata effettuata nessuna prenotazione
    //altrimenti mostra le lezioni prenotate mediante un ciclo for
    public void mostraLezioniPrenotate()
    {
        TextView elencoLezioniPrenotate = (TextView) findViewById(R.id.elencoLezioniPrenotate);

        String giorniLezionePrenotati = "";
        String divisioneGiorniLezionePrenotati = "";

        //se non si è prenotati a nessuna lezione mostra un messaggio
        if(lezioniPrenotateArray.length == 0)
        {
            giorniLezionePrenotati = "Nessuna lezione prenotata";
        }
        else
        {
            for (int i = 0; i < lezioniPrenotateArray.length; i++)
            {
                //controllo se non è vuoto il valore
                if(lezioniPrenotateArray[i].isEmpty())
                {
                    continue;
                }

                //se è l'ultimo elemento mette la congiungione e, altrimenti la virgola (se sono presenti ppiù prenotazioni)
                if(i == lezioniPrenotateArray.length - 1 && i > 0)
                {
                    divisioneGiorniLezionePrenotati = " e ";
                }
                else if(i > 0)
                {
                    divisioneGiorniLezionePrenotati = ", ";
                }

                giorniLezionePrenotati = giorniLezionePrenotati + divisioneGiorniLezionePrenotati + lezioniPrenotateArray[i];
            }
        }

        //mostra le lezizoni prenotate
        elencoLezioniPrenotate.setText(giorniLezionePrenotati);
    }


    //effettua il logout e torna all'attività precedente di login
    public void logout(View view)
    {
        logout = new Intent(CalendarMain.this, MainActivity.class);

        //messaggio di logout eseguito
        Toast.makeText(CalendarMain.this,"Hai effettuato il logout",Toast.LENGTH_LONG).show();

        startActivity(logout);
    }


    //controllo se una lezione è già stata prenotata o no in modo da far prenotare l'utente o togliere la prenotazione
    public void prenotaDisiscriviLezione(String giornoPrenotato, Button idButton)
    {
        if(giornoPrenotato.isEmpty())
        {
            Toast.makeText(CalendarMain.this,"C'è stato un problema durante la prenotazione e sprenotazione della lezione",Toast.LENGTH_LONG).show();
        }
        else if(!Arrays.asList(lezioniPrenotateArray).contains(giornoPrenotato.trim()))
        {
            //inserisco nell'array il giorno prenotato se non è presente
            lezioniPrenotateArray = Arrays.copyOf(lezioniPrenotateArray, lezioniPrenotateArray.length + 1);
            lezioniPrenotateArray[lezioniPrenotateArray.length - 1] = giornoPrenotato;

            //cambio il background e il testo del bottone corrispondente alla lezione
            idButton.setText("Disdici");
            idButton.setBackgroundColor(0xFFB30000);

            //mostro il messaggio che si è prenotato alla lezione
            Toast.makeText(CalendarMain.this,"Ti sei prenotato alla lezione di " + giornoPrenotato, Toast.LENGTH_LONG).show();
        }
        else if(Arrays.asList(lezioniPrenotateArray).contains(giornoPrenotato.trim()))
        {
            for (int i = 0; i < lezioniPrenotateArray.length; i++)
            {
                if(lezioniPrenotateArray[i] != giornoPrenotato)
                {
                    lezioniPrenotateArrayTMP = Arrays.copyOf(lezioniPrenotateArrayTMP, lezioniPrenotateArrayTMP.length + 1);
                    lezioniPrenotateArrayTMP[lezioniPrenotateArrayTMP.length - 1] = giornoPrenotato;
                }
            }

            lezioniPrenotateArray = lezioniPrenotateArrayTMP;

            //cambio il background e il testo del bottone corrispondente alla lezione
            idButton.setText("Prenota");
            idButton.setBackgroundColor(0xFF7FFF00);

            //mostro il messaggio che ha disdetto la prenotazione
            Toast.makeText(CalendarMain.this,"Hai disdetto la prenotazione alla lezione di " + giornoPrenotato, Toast.LENGTH_LONG).show();
        }
    }
}
