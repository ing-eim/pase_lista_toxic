/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbi.paselista.base;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author LALO-DOCIZ
 */
public class JCSession {
    public JCSession(){
        
    }
    
    public void CreateSession(HttpServletRequest request){
        HttpSession misession= request.getSession(true);
        misession.setAttribute("c_v_session", new JCGlobals().getid_session());
        misession.setAttribute("noemp", new JCGlobals().getNoEmp());        
        misession.setAttribute("adsc", new JCGlobals().getDescAdsc());
        misession.setAttribute("puesto", new JCGlobals().getDescPuesto());
        misession.setAttribute("idadsc", new JCGlobals().getidAdsc());
    }
    
    public void CloseSession(HttpServletRequest request){
        HttpSession s =  request.getSession();
        s.invalidate();
    }
}
