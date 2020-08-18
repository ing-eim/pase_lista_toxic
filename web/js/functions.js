/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
$(document).ready(function() {     
    $("form").submit(function(e){
        e.preventDefault();
        var us = $("#txtempleado").val();
        var pwd = $("#txtpassword").val();        
        $.ajax({
			url : 'log_in',
                        type: 'post',
			data : {
				us : us,
                                pwd : pwd
			},
			success : function(responseText) {                                
                                var s =  responseText.split("|");
				if(s[0] === true || s[0]=== "true"){
                                    window.location.href = 'sup_eval_corp.jsp';
                                }else{                                    
  
                                    $("#msg_sal").html(s[1]);
                                }                              
                                
			}
		});
   });    
});
*/
$(document).ready(function() {     
    /*$("#validar").click(function(){
        SearchPersona();
    });*/
    $("#c_i_placa").keyup(function(e){
        if(e.keyCode === 13)
            SearchPersona();
    });
    
    

});
$(window).keyup(function(event){
	if(event.keyCode==115){
		RegistrarPersonal();
		Limpiar();
	}
	if(event.keyCode==27){
		Limpiar();
	}
});

function Limpiar(){
	$("#valido").val(0);
        $("#resultado").html("");
        $("#imagen").html("");
        $("#c_i_placa").val("");
        $("#c_i_placa").focus();
}

function SearchPersona(){
    var c_i_placa = $("#c_i_placa").val();
    $.ajax({
        url     : 'search_personal',
        type    : 'post',
        data    : { c_i_placa : c_i_placa },
        success : function(resp){
            $("#resultado").html(resp);            
        },
        complete: function(){
            var imagen = "<center><img src='buscarfoto?c_i_placa="+c_i_placa+"' width='300px' height='400px'></center>";
            $("#imagen").html(imagen);
        }
    });    
}

function RegistrarPersonal(){   
   if($("#valido").val()==0){
	alert("EL PERSONAL NO PUEDE REVALIDAR LOC");
   }else{
        var valor2=$("#c_i_placa").val();
        $.ajax({
           url:'RegistroAsistencia' ,
           type:'post',
           data:{c_i_placa:valor2},
           success:function(resp){
               var r = resp.split("|");
               alert(r[1]);
           }
        });	
   }
}
