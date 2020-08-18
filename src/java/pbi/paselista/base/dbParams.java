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
public class dbParams {
    private String dbHost;
    private String dbUser;
    private String dbPwd;
    private String dbPort;
    private String dbController;
    
    public dbParams(){
        setdbHostMSProd();
        setdbUserMSProd();
        setdbPwdMSProd();
        setdbPortMSProd();
        setdbControllerMSProd();
    }
    
    private void setdbHostMSProd(){ this.dbHost = "jdbc:mysql://172.30.1.131:3366/db_pbi_biometrico"; }
    public String getdbHostMSProd(){return dbHost;}
   
    private void setdbUserMSProd(){ this.dbUser = "pbidis"; }
    public String getdbUserMSProd(){return dbUser;}
   
    private void setdbPwdMSProd(){ this.dbPwd = "pb1d1z"; }
    public String getdbPwdMSProd(){return dbPwd;}
   
    private void setdbPortMSProd(){ this.dbPort = "3366"; }
    public String getdbPortMSProd(){return dbPort;}
   
    private void setdbControllerMSProd(){ this.dbController = "com.mysql.jdbc.Driver"; }
    public String getdbControllerMSProd(){ return this.dbController;}
}
