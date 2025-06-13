package cm.gl2b.cc4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Spinner spChoix;
    private Spinner deviseChoix;
    private Button bouton;

    private EditText montantDevise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        montantDevise = (EditText) findViewById(R.id.saisiMontant);
        spChoix = (Spinner) findViewById(R.id.choixDevise);
        deviseChoix = (Spinner) findViewById(R.id.deviseArriver);

        String[] devises = {"Euro", "Livre sterling", "Dollar American", "Dirham", "Franc CFA", "Dollar Canadian"};
        //construire l'adaptateur
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, devises);

        //brancher bl'adaptateur

        spChoix.setAdapter(adapter);
        deviseChoix.setAdapter(adapter);

        bouton = (Button) findViewById(R.id.btnConvertir);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double somme = Double.parseDouble(montantDevise.getText().toString());
                String deviseDepart = spChoix.getSelectedItem().toString();
                String deviseArriver = deviseChoix.getSelectedItem().toString();

                Double result = convertion(somme, deviseDepart, deviseArriver);

                TextView resultTextV = findViewById(R.id.idRESULT);
                resultTextV.setText(String.format(" %.2f %s ",result, deviseArriver));
            }
        });
    }

    private double convertion(double amount, String deviseDepart, String deviseArriver) {
        Double mtn1 = getExchange(deviseDepart);
        double mt2 = getExchange(deviseArriver);
        return amount * (mt2 / mtn1);
    }

    private double getExchange(String str) {
        if (str == "Euro") {
            return 0.82;
        } else if (str == "Livre sterling") {
            return 0.72;
        } else if (str == "Dollar American") {
            return 1.00;
        } else if (str == "Dirham") {
            return 3.67;
        } else if (str == "Franc CFA") {
            return 556.04;
        } else if (str == "Dollar Canadian") {
            return 1.24;
        } else {
            return 0.00;
        }
    }
}
