/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 $(function(){
        $("#frmfilecomision").on("submit", function(e){
            e.preventDefault();
            var f = $(this);
            var formData = new FormData(document.getElementById("frmfilecomision"));
            formData.append("type_file_load", 1);
            formData.append(f.attr("name"), $(this)[0].type_file_load);
            
            //formData.append("dato", "valor");
            //formData.append(f.attr("name"), $(this)[0].files[0]);
            $.ajax({
                url: "UploadFiles",
                type: "post",
                dataType: "html",
                data: formData,
                cache: false,
                contentType: false,
	        processData: false
            })
                .done(function(res){
                        var r = res.split("|");
                        alert(r[1]);
                });
        });
        
        
        $("#frmfilecorrectivo").on("submit", function(e){
            e.preventDefault();
            var f = $(this);
            var formData = new FormData(document.getElementById("frmfilecorrectivo"));
            formData.append("type_file_load", 2);
            formData.append(f.attr("name"), $(this)[0].type_file_load);
            //formData.append("dato", "valor");
            //formData.append(f.attr("name"), $(this)[0].files[0]);
            $.ajax({
                url: "UploadFiles",
                type: "post",
                dataType: "html",
                data: formData,
                cache: false,
                contentType: false,
	        processData: false
            }).done(function(res){
                        var r = res.split("|");
                        alert(r[1]);
                });
        });        
        
        $("#txtc_i_placaobs").keyup(function(e){
                var l = $(this).val().length;                
                if ( l > 0){
                    $("#btnagregaobs").css({'display': 'block'});
                }else{
                    $("#btnagregaobs").css({'display': 'none'});
                }               
                if(e.keyCode === 13){                    
                    searchobservaciones(); 
                }
        })
        
        
});
    
function UploadQueja(){
        var c_i_placa = $("#txtc_i_placaquejas").val();
        var queja = $("#txtqueja").val();
        $.ajax({
           url: 'LoadQueja',
           type: 'post',
           data:{c_i_placa:c_i_placa,queja:queja},
           success:function (resp){
                        var r = resp.split("|");
                        alert(r[1]);
           }
        });
    }
    
    function UploadEstimulo(){
        var c_i_placa = $("#txtc_i_placaescon").val();
        var tipoestimulo = $("#txttipoestimulo").val();
        var motivo = $("#txtmotivo").val();
        $.ajax({
           url: 'LoadEstimulos',
           type: 'post',
           data:{c_i_placa:c_i_placa,tipoestimulo:tipoestimulo,actividad:motivo},
           success:function (resp){
                var r = resp.split("|");
                alert(r[1]);
           }
        });
    }
    
    

    function searchobservaciones(){
        var c_i_placa = $("#txtc_i_placaobs").val();
        $.ajax({
           url: 'SearchObservacion',
           type: 'post',
           data:{c_i_placa:c_i_placa},
           success:function (resp){               
                $("#tblobservaciones").html(resp);
           }
        });
    }

    function UploadObservacion(){
        var c_i_placa = $("#txtc_i_placaobs").val();
        var f_d_observacion = $("#txtfechaobservacion").val();
        var observaciones = $("#txtobservaciones").val();
        $.ajax({
           url: 'LoadObservacion',
           type: 'post',
           data:{c_i_placa:c_i_placa,f_d_observacion:f_d_observacion,observaciones:observaciones},
           success:function (resp){
                var r = resp.split("|");
                //alert(r[1]);
                if(r[0] === true || r[0] === 'true')
                    searchobservaciones();
                else
                    alert(r[1]);
           }
        });
    }
    
    
    function UpdateObservacion(){
        var pk_i_obs = $("#pk_i_obs").val();
        var f_d_observacion = $("#txtfechaobservacion_edit").val();
        var observaciones = $("#txtobservaciones_edit").val();
        $.ajax({
           url: 'UpdateObservacion',
           type: 'post',
           data:{pk_i_obs:pk_i_obs,f_d_observacion:f_d_observacion,observaciones:observaciones},
           success:function (resp){
                var r = resp.split("|");                
                if(r[0] === true || r[0] === 'true')
                    searchobservaciones();
                else
                    alert(r[1]);
           }
        });
    }
    
    function CargaDatosEditaObs(element,pk_i_observacion){        
        //var cveobservacion = ($(td).parent().parent().find("td").eq(1).text()).trim();
	
	var observacion = ($(element).parent().parent().find("td").eq(1).text()).trim();
	var fecha = ($(element).parent().parent().find("td").eq(2).text()).trim();	
        alert(observacion);
        $("#txtfechaobservacion_edit").val(fecha);
        $("#txtobservaciones_edit").val(observacion);
        $("#pk_i_obs").val(pk_i_observacion);

    }
    
    function DeleteObs(pk_i_observacion){
        
        if (confirm("¿Está seguro de eliminar ésta Observación al Elemento?")){
            $.ajax({
                url: 'DeleteObservacion',
                type: 'post',
                data:{pk_i_obs:pk_i_observacion},
                success:function (resp){
                    var r = resp.split("|");                
                    if(r[0] === true || r[0] === 'true')
                        searchobservaciones();
                    else
                        alert(r[1]);
                }
            });
        }
    }
    
    function clearformobservaciones(){
         $("#txtfechaobservacion").val("");
         $("#txtobservaciones").val("");
    }
