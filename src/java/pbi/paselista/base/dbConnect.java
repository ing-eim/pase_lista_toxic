/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbi.paselista.base;
/**
 *
 * @author LALO-DOCIZ
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
import pbi.paselista.base.dbParams;
public class dbConnect {
    protected Connection lo_scon = null;
    protected Statement lo_stm = null;
    protected PreparedStatement lo_pstm = null;
    protected ResultSetMetaData lo_md = null;
    protected ResultSet lo_result = null;
    
    protected String strSQL = null;
    private String dbPool = null;
    
    private Connection conn;
   
    public dbConnect(){
        try{
            
            dbParams p = new dbParams();
            Class.forName(p.getdbControllerMSProd());
            this.conn=DriverManager.getConnection(p.getdbHostMSProd(),p.getdbUserMSProd(),p.getdbPwdMSProd());
            System.out.println("ConectadoMYSQL");
        }catch(Exception e){
            System.out.println("¡ ERROR !"+e.getMessage());
        }
    }

    
    public Connection getConnection(){
        return this.conn;
    }
    

    
}

