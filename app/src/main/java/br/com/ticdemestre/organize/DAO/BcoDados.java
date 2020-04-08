package br.com.ticdemestre.organize.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BcoDados extends SQLiteOpenHelper
{
    public static final String tab_controle = "controle";
    public static final String con_conCodigo    = "conCodigo";
    public static final String con_conDescricao = "conDescricao";

    public static final String tab_colaborador = "colaborador";
    public static final String col_conCodigo = "conCodigo";
    public static final String col_usuCodigo = "usuCodigo";
    public static final String col_colTipo   = "colTipo";
    public static final String col_colStatus = "colStatus";

    public static final String tab_conta = "conta";
    public static final String cta_ctaCodigo    = "ctaCodigo";
    public static final String cta_ctaDescricao = "ctaDescricao";

    private static final String DATABASE_NAME = "organizeTICdeMestre.db";
    private static final int DATABASE_VERSION = 1;

    //Estrutura da tabela controle (sql statement)
    private static final String create_controle = "create table " +
            tab_controle   + "( " + con_conCodigo + " integer primary key autoincrement, " +
            con_conDescricao  + " text not null );";

    //Estrutura da tabela colaborador (sql statement)
    private static final String create_colaborador = "create table " +
            tab_colaborador   + "( " + col_conCodigo + " integer primary key autoincrement, " +
            col_usuCodigo  + " integer not null, " +
            col_usuCodigo  + " integer not null, " +
            col_colTipo    + " text not null, " +
            col_colStatus  + " text not null );";

    //Estrutura da tabela controle (sql statement)
    private static final String create_conta = "create table " +
            tab_conta   + "( " + cta_ctaCodigo + " integer primary key autoincrement, " +
            cta_ctaDescricao  + " text not null );";

    public BcoDados(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Criação das tabelas
        db.execSQL(create_controle);
        db.execSQL(create_colaborador);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // db.execSQL(alt_tab_);
    }
}