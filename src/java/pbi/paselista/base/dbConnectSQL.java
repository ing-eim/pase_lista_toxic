/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbi.paselista.base;

/**
 *
 * @author depdes10
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pbi.paselista.base.dbParamsSQL;
public class dbConnectSQL {
    protected Connection lo_scon = null;
    protected Statement lo_stm = null;
    protected PreparedStatement lo_pstm = null;
    protected ResultSetMetaData lo_md = null;
    protected ResultSet lo_result = null;
    
    protected String strSQL = null;
    private String dbPool = null;
    
    private Connection conn;
   
    public dbConnectSQL(){
        try{
            
            dbParamsSQL p = new dbParamsSQL();
            Class.forName(p.getdbControllerSProd());
            this.conn=DriverManager.getConnection(p.getdbHostSProd(),p.getdbUserSProd(),p.getdbPwdSProd());
            System.out.println("ConectadoSQL");
        }catch(Exception e){
            System.out.println("¡ ERROR !"+e.getMessage());
        }
    }

    
    public Connection getConnection(){
        return this.conn;
    }
    

    
}


