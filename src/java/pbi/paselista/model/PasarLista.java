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
        String salida="";
        Connection conn = new dbConnect().getConnection();   
        //JCGlobals jc = new JCGlobals();        
        int nr = 0;                
        try{
            String query = "{call consultasSiudd.sp_search_pers_toxicologico(?)}";
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
                    salida ="<center><div class='form-control'>"
                                 +     rs.getString("nIdNumEmp")+" - "+rs.getString("tApePaterno")+" "+rs.getString("tApeMaterno")+" "+rs.getString("tNombre")
                                 + "</div></center>";                    
                    /*table += "<tr><td>"+nr+"</td>"
                            +   "<td>"+rs.getString("d_v_observacion")+"</td>"
                            +   "<td>"+rs.getString("f_d_observacion")+"</td>"
                            +   "<td><button class='btn btn-info' onclick ='CargaDatosEditaObs(this,"+rs.getString("pk_i_observacion")+")' data-toggle=\"modal\" data-target=\"#ModalEditaObservacion\">Editar</button></td>"
                            +   "<td><button class='btn btn-danger' onclick='DeleteObs("+rs.getString("pk_i_observacion")+")'>Eliminar</button></td>"
                            + "</tr>";                    */
                }                
                hadResults = stmt.getMoreResults();
            }
            if (nr == 0){
                salida ="<div><center> SIN REGISTROS </center></div>";
            }
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
}
