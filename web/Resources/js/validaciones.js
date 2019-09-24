/* 
 * Funciones de validación de documento
 */

function validarLogin()
{ 
 var usuario = document.form.usuario.value; 
 var password = document.form.password.value;
 
 if (usuario==null || usuario=="")
 { 
 alert("El nombre de usuario es requerido"); 
 return false; 
 }
 else if(password==null || password=="")
 { 
 alert("Ingrese su contraseña"); 
 return false; 
 } 
}

function validarRegistro()
{ 
 var usuario = document.form.usuario.value; 
 var password = document.form.password.value;
 var validarPassword = document.form.validarPassword.value;
 var email = document.form.email.value; 
 
 if (email==null || email=="")
 { 
    alert("El email es requerido"); 
    return false; 
 } 
 if (usuario==null || usuario=="")
 { 
    alert("El nombre de usuario es requerido"); 
    return false; 
 }
 if(password==null || password=="")
 { 
    alert("Ingrese su contraseña"); 
    return false; 
 } 
 if(validarPassword==null || validarPassword=="")
 { 
    alert("Ingrese su contraseña de nuevo"); 
    return false; 
 } 
 if(validarPassword !== password)
 { 
    alert("Las contraseñas no coinciden"); 
    return false; 
 } 

}


