<%-- 
    Document   : app-paselista
    Created on : 14/08/2020, 11:56:57 AM
    Author     : depdes10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <script src="./js/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="./js/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="./js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="./js/jquery-3.5.0.min.js"></script>                    
        <script src="./js/functions.js"></script>
    </head>
    <body>
        <nav class="container">
            <center><img src='./images/loginu.png'></center>
        </nav>
        <div class="container">
                <div class = "row" >
                    <div class = "col-sm-5" >
                    </div>
                    <div class = "col-sm-2" >
                        <input type="text" class="form-control" id="c_i_placa" name="c_i_placa" value=""/>
                    </div>                                    
                    <div class = "col-sm-5" >
                    </div>
                </div>
                <div class = "row">
                    <div class = "col-sm-5">                       
                    </div>
                    <div class = "col-sm-2">                        
                        <input type="button" class="form-control btn-success" value="Validar" id="validar"/>
                    </div>
                    <div class = "col-sm-5" >
                    </div>
                </div>                     
        </div>
        <div class="container" id="resultado">                  
        </div>
        <div class="container" id="imagen">                  
        </div>
    </body>
</html>
