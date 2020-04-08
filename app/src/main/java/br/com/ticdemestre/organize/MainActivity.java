package br.com.ticdemestre.organize;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    String lang = "pt";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        lerIdioma();

        setContentView(R.layout.activity_main);

        carregarImagemIdioma();

        SharedPreferences dados = getSharedPreferences("organizeTICdeMestre", MODE_PRIVATE);
        Integer conectado = dados.getInt("login", 0);
        if( conectado == 0 )
        {
            Intent telaLogin = new Intent(this, LoginActivity.class);
            startActivityForResult(telaLogin, 2);
        }

        dataPeriodo();
    }

    public void dataPeriodo()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Button periodo = (Button) findViewById(R.id.btnPeriodo);

        Calendar primeiroDia = Calendar.getInstance();
        primeiroDia.set(Calendar.DAY_OF_MONTH, 1);

        Calendar ultimoDia = Calendar.getInstance();
        ultimoDia.add(Calendar.MONTH, 1);
        ultimoDia.set(Calendar.DAY_OF_MONTH, 1);
        ultimoDia.add(Calendar.DAY_OF_MONTH, -1);

        periodo.setText( getString( R.string.txtPeriodo ) + ": " + sdf.format( primeiroDia.getTime() ) + " : " + sdf.format( ultimoDia.getTime() ) );
    }

    public void lerIdioma()
    {
        SharedPreferences dados = getSharedPreferences("organizeTICdeMestre", MODE_PRIVATE);
        lang = dados.getString("idioma", "pt");

        Locale idioma = new Locale(lang);
        Locale.setDefault(idioma);

        Context context = this;
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());

        config.setLocale(idioma);
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    public void carregarImagemIdioma()
    {
        ImageView imagem = (ImageView) findViewById(R.id.imgIdioma);

        String nomeImagem = "@drawable/" + lang;
        int imageResource = getResources().getIdentifier(nomeImagem, null, getPackageName());

        Drawable res = ContextCompat.getDrawable(getApplicationContext(), imageResource);

        imagem.setImageDrawable(res);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == 1 )
        {
            recreate();
        }

        if( requestCode == 2 )
        {
            recreate();
        }
    }

    public void telaConfig(View v)
    {
        Intent tela = new Intent(this, ConfigActivity.class);
        startActivityForResult(tela, 1);
    }

    public void sair(View v)
    {

        SharedPreferences.Editor dados = getSharedPreferences("organizeTICdeMestre", MODE_PRIVATE).edit();
        dados.putInt("login", 0);
        dados.commit();

        recreate();
    }
}
