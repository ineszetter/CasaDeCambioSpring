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

