/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function c_s(){
    
   if (confirm("Desea Cerrar su Sesión")){
    $.ajax({
        url : 'log_out',
        type: 'post',
        beforeSend: function(r){
          $("#session_progress").html('<div class="spinner-grow text-danger" role="status"><span class="sr-only">Cerrando Sesión...</span></div>');
        },
        success: function(resp){            
             var s =  resp.split("|");
	     if(s[0] === true || s[0]=== "true"){
                   window.location.href = 'lg.jsp';
             }else{                                    
                alert("ERROR "+s[1]);
                $("#session_progress").html("");
             }
        }
        
    });
    }
}


