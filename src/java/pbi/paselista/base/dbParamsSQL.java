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
public class dbParamsSQL {
    private String dbHost;
    private String dbUser;
    private String dbPwd;
    private String dbPort;
    private String dbController;
    
    public dbParamsSQL(){
        setdbHostSProd();
        setdbUserSProd();
        setdbPwdSProd();
        setdbPortSProd();
        setdbControllerSProd();
    }
    
    private void setdbHostSProd(){ this.dbHost = "jdbc:sqlserver://172.30.16.189"; }
    public String getdbHostSProd(){return dbHost;}
   
    private void setdbUserSProd(){ this.dbUser = "sa"; }
    public String getdbUserSProd(){return dbUser;}
   
    private void setdbPwdSProd(){ this.dbPwd = "sqlserver2005"; }
    public String getdbPwdSProd(){return dbPwd;}
   
    private void setdbPortSProd(){ this.dbPort = "1433"; }
    public String getdbPortSProd(){return dbPort;}
   
    private void setdbControllerSProd(){ this.dbController = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; }
    public String getdbControllerSProd(){ return this.dbController;}
}
