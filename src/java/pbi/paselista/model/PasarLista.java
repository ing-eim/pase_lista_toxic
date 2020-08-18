/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbi.paselista.model;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import pbi.paselista.base.JCGlobals;
import pbi.paselista.base.dbConnect;
import pbi.paselista.base.dbConnectSQL;
/**
 *
 * @author depdes10
 */
public class PasarLista {
    private int c_i_placa;
    
    public PasarLista(int c_i_placa){
        this.c_i_placa = c_i_placa;
    }
    
    public String BuscarPersonal () throws IOException{
        String salida = "";
        String table =  "<table>";
        String tipopersonal = "";
        Connection conn = new dbConnect().getConnection();
        //JCGlobals jc = new JCGlobals();        
        int nr = 0;                
        try{
            String query = "{call db_pbi_biometrico.sp_search_pers_toxicologico(?)}";
            CallableStatement stmt;
            stmt = conn.prepareCall(query);
            stmt.setInt(1,this.c_i_placa);
            boolean hadResults = stmt.execute();
             // print headings            
            System.out.println("========= OBTENIEDO INFO DEL PERSONAL  =======================");            
            while (hadResults) {
                ResultSet rs = stmt.getResultSet();
                
                while(rs.next()){
                    nr++;
                    /*salida ="<center><div class='form-control'>"
                                 
                                 +   rs.getString("c_i_placa")+" - "+rs.getString("tApePaterno")+" "+rs.getString("tApeMaterno")+" "+rs.getString("tNombre")
                                 + "</div></center>";     */               
                    
                    tipopersonal="";
                    if (rs.getInt("c_i_tipopersonal")== 1 ){
                        tipopersonal="<strong>REVALIDACION</strong>";
        	    }else{
                        tipopersonal="<strong>INCLUSION</strong>";
                    }

                    table += "<tr><td></td><td style='text-align:center'>"+tipopersonal+"</td></tr>"
                            +"<tr><td>Placa: </td><td>"+rs.getString("c_i_placa")+"</td></tr>"
                            +"<tr><td>Grado: </td><td>"+rs.getString("d_v_grado")+"</td></tr>"
                            +"<tr><td>Nombre: </td><td>"+rs.getString("Nombre")+"</td></tr>"
                            +"<tr><td>Sector: </td><td>"+rs.getString("Sector")+"</td></td>";
                    if(rs.getInt("pk_i_persona") > 0){
                       	table += "<tr><td>EL ELEMENTO YA FUE REGISTRADO</td></tr>"+
                                 "<tr><td><input type='hidden' id='valido' value=0></td></tr>";
		    }else{
                        table += " <tr><td colspan='2'><button type='submit' class='btn btn-success btn-block' onclick='RegistrarPersonal();'>REGISTRO</button></td></tr>"+
			         "<tr><td><input type='hidden' id='valido' value=1></td></tr>";						
                    }                            
                            
                }                
                hadResults = stmt.getMoreResults();
            }
            if (nr == 0){
                salida ="<div><center> PERSONAL NO SE ENCUENTRA EN LISTADO </center></div>";
            }else
                salida = table + "</table>";
            stmt.close();
            conn.close();
        }catch(Exception w){
            System.out.println("¡ ERROR !"+w.getMessage());            
            w.printStackTrace();
        }       
       return salida;
    }
    
    
    public byte[] LoadImage(){    
        String s = "";
        Connection conn = new dbConnectSQL().getConnection();   
        //JCGlobals jc = new JCGlobals();        
        int nr = 0;                
        byte[] img = null;
        
        try{
            String queryString = "SELECT top 1 " +
                                 "       a.num_persona, " +
                                 "	    b.imagen    " +
                                 "   from [ssp_pbi_db].[dbo].[PERSONA] a     " +
                                 "   join [ssp_pbi_db].[dbo].[FOTO] b on (a.persona_id = b.persona_id) " +
                                 " where " +
                                 "      b.t_foto_id = 3166  " +
                                 " and  a.num_persona = ? order by b.foto_id desc;";
            PreparedStatement statement = conn.prepareStatement(queryString);
            statement.setInt(1,this.c_i_placa);
            ResultSet rs = statement.executeQuery();
            int c=0;
            System.out.println("4.- Obteniendo Foto .... Espere Por Favor...");
            while (rs.next()) {                                
                img =   rs.getBytes("imagen");
                System.out.println(rs.getString("num_persona"));                
                c++;
                
            }            
            conn.close();
        }catch(Exception w){
            System.out.println("¡ ERROR !"+w.getMessage());            
            w.printStackTrace();
        }
        return img;
    }
    
    private Image getImage(byte[] bytes, boolean isThumbnail) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis; // File or InputStream
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        if (isThumbnail) {
            param.setSourceSubsampling(4, 4, 0, 0);
        }
        return reader.read(0, param);
    }    
    
    public boolean RegistraAsistencia () throws IOException{
        int numerror = 0;
        
        Connection conn = new dbConnect().getConnection();
        JCGlobals jc = new JCGlobals();        
        int nr = 0;                
        try{
            String query = "{call db_pbi_biometrico.sp_registra_asist_toxic(?)}";
            CallableStatement stmt;
            stmt = conn.prepareCall(query);
            stmt.setInt(1,this.c_i_placa);
            boolean hadResults = stmt.execute();
             // print headings            
            System.out.println("========= OBTENIEDO INFO DEL PERSONAL  =======================");            
            while (hadResults) {
                ResultSet rs = stmt.getResultSet();
                
                while(rs.next()){
                       numerror = rs.getInt("error");
                       jc.setMsg(rs.getString("msg"));
                }                
                hadResults = stmt.getMoreResults();
            }            
            stmt.close();
            conn.close();
            if(numerror == 0){
                return true;
            }else{
                return false;
            }
        }catch(Exception w){
            System.out.println("¡ ERROR !"+w.getMessage());            
            w.printStackTrace();
            return false;
        }              
    }
    
    
    
}
