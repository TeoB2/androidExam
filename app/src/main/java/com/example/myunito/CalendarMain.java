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
    TextView lezioniPrenotate;
    public String giorniLezione = "";
    Intent logout;

    //array con le lezioni prenotate da una matricola
    String[] lezioniPrenotateArray = {};

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
                //mostra il messaggio che è stato premuto il bottone lunedi
                Toast.makeText(CalendarMain.this,"Ti sei prenotato alla lezione di Lunedì", Toast.LENGTH_LONG).show();

                prenotaDisiscriviLezione("Lunedì", lunedi);

                //concatena il giorno alla stringa
                giorniLezione = giorniLezione + "\nLunedì";

                //lo stampa sullo schermo
                mostraLezioniPrenotate("Lunedì");

                //disattiva il bottone per prenotarsi alla lezione di lunedì e cambia il testo in "prenotata"
                lunedi.setText("X");
                lunedi.setBackgroundColor(0xFFB30000);
            }
        });

        //al click del bottone martedi entra nel metodo
        martedi.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v)
            {
                //mostra il messaggio che è stato premuto il bottone martedi
                Toast.makeText(CalendarMain.this,"Ti sei prenotato alla lezione di Martedì",Toast.LENGTH_LONG).show();

                prenotaDisiscriviLezione("Martedì", martedi);

                //concatena il giorno alla stringa
                giorniLezione = giorniLezione + "\nMartedì";

                //lo stampa sullo schermo
                mostraLezioniPrenotate(giorniLezione);

                //disattiva il bottone per prenotarsi alla lezione di lunedì e cambia il testo in "prenotata"
                martedi.setEnabled(false);
                martedi.setText("Prenotata");
            }
        });

        //al click del bottone mercoledi entra nel metodo
        mercoledi.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v)
            {
                //mostra il messaggio che è stato premuto il bottone mercoledi
                Toast.makeText(CalendarMain.this,"Ti sei prenotato alla lezione di Mercoledì",Toast.LENGTH_LONG).show();

                prenotaDisiscriviLezione("Mercoledì", mercoledi);

                //concatena il giorno alla stringa
                giorniLezione = giorniLezione + "\nMercoledì";

                //lo stampa sullo schermo
                mostraLezioniPrenotate(giorniLezione);

                //disattiva il bottone per prenotarsi alla lezione di lunedì e cambia il testo in "prenotata"
                mercoledi.setEnabled(false);
                mercoledi.setText("Prenotata");
            }
        });

        //al click del bottone giovedi entra nel metodo
        giovedi.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v)
            {
                //mostra il messaggio che è stato premuto il bottone giovedi
                Toast.makeText(CalendarMain.this,"Ti sei prenotato alla lezione di Giovedì",Toast.LENGTH_LONG).show();

                prenotaDisiscriviLezione("Giovedì", giovedi);

                //concatena il giorno alla stringa
                giorniLezione = giorniLezione + "\nGiovedì";

                //lo stampa sullo schermo
                mostraLezioniPrenotate(giorniLezione);

                //disattiva il bottone per prenotarsi alla lezione di lunedì e cambia il testo in "prenotata"
                giovedi.setEnabled(false);
                giovedi.setText("Prenotata");
            }
        });

        //al click del bottone venerdi entra nel metodo
        venerdi.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v)
            {
                //mostra il messaggio che è stato premuto il bottone venerdi
                Toast.makeText(CalendarMain.this,"Ti sei prenotato alla lezione di Venerdì",Toast.LENGTH_LONG).show();

                prenotaDisiscriviLezione("Venerdì", venerdi);

                Toast.makeText(CalendarMain.this,Arrays.toString(lezioniPrenotateArray),Toast.LENGTH_LONG).show();

                //concatena il giorno alla stringa
                giorniLezione = giorniLezione + "\nVenerdì";

                //lo stampa sullo schermo
                mostraLezioniPrenotate(giorniLezione);

                //disattiva il bottone per prenotarsi alla lezione di lunedì e cambia il testo in "prenotata"
                venerdi.setEnabled(false);
                venerdi.setText("Prenotata");
            }
        });

        //entra nel metodo passandogli la stringa giorniLezione
        mostraLezioniPrenotate(giorniLezione);
    }

    //se la stringa giorniLezione è vuota mostra un messaggio che non è stata effettuata nessuna prenotazione
    //altrimenti mostra la stringa con tutti i giorni di lezioni prenotati
    public void mostraLezioniPrenotate(String giorniLezione)
    {
        TextView elencoLezioniPrenotate = (TextView) findViewById(R.id.elencoLezioniPrenotate);

        //controllo se la stringa è vuota
        if(giorniLezione.isEmpty())
        {
            giorniLezione = "Nessuna prenotazione";
        }

        elencoLezioniPrenotate.setText(giorniLezione);
    }

    //effettua il logout e torna all'attività precedente
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
            lezioniPrenotateArray = Arrays.copyOf(lezioniPrenotateArray, lezioniPrenotateArray.length + 1);
            lezioniPrenotateArray[lezioniPrenotateArray.length - 1] = giornoPrenotato;

            idButton.setText("X");
            idButton.setBackgroundColor(0xFFB30000);
        }
        /*else
        {
            for(int i = 0; i < lezioniPrenotateArray.length; i++)
            {
                if(giornoPrenotato == lezioniPrenotateArray[i])
                {
                    lezioniPrenotateArray = removeTheElement(lezioniPrenotateArray, giornoPrenotato);
                }
            }

            idButton.setText("X");
            idButton.setBackgroundColor(0xFFB30000);
        }*/
    }
}
