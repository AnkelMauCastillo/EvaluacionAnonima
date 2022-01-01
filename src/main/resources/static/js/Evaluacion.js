function seleccionarCurso(){
	var cod = document.getElementsById("curso-select");
	var selected = cod.options[combo.selectedIndex].text
	alert(selected);
	
};



var iniciarSesion = function() {
	

	var email = $("#correo").val();
	var password = $("#contrasenia").val();

	
	$.post("/usuario/login", { 'correo': email, 'contrasenia': password }, /*callback*/ function( fragmento ) {
 			$("#contenedor").replaceWith(fragmento);
	});
};

var registrarUsuario = function() {
	
	var nombre = $("#nombre").val();
	var apellidopat = $("#apellidoPat").val();
	var apellidomat = $("#apellidoMat").val();
	var matricula = $("#matricula").val();
	var correo = $("#correo").val();
	var password = $("#contrasenia").val(); 
	// falta el curso 
	$.get("/usuario/registro", {'nombre':nombre,'apellidoPat':apellidopat,'aplliedoMat':apellidomat,'matricula':matricula,
	       'correo':correo, 'contrasenia':password },
	       function( fragmento ) {
 			$("#contenedor").replaceWith(fragmento);
	});
};
// registro de curso 

var guardarCurso = function() {
	var nombre =$("nombre").val();
	var descripcion =$("#descripcion").val();
	$.get("curso/guardarcurso", {'nombre':nombre , 'descripcion':descripcion},
	function(fragmento){
		$("#contenedor").replaceWith(fragmento);
	});
};

$(document).ready(function () {
	
	
	 jQuery.validator.addMethod("sololetrasyespacios", function (value, element) {
        return this.optional(element) || /^[a-z\s]+$/i.test(value);
    }, "Solo letras y espacios");

	
	jQuery.extend(jQuery.validator.messages, {
        required: "Este campo es obligatorio.",
        remote: "Por favor, rellena este campo.",
        email: "Por favor, escribe una dirección de correo válida",
        url: "Por favor, escribe una URL válida.",
        date: "Por favor, escribe una fecha válida.",
        dateISO: "Por favor, escribe una fecha (ISO) válida.",
        number: "Por favor, escribe un número entero válido.",
        digits: "Por favor, escribe sólo dígitos.",
        creditcard: "Por favor, escribe un número de tarjeta válido.",
        equalTo: "Por favor, escribe el mismo valor de nuevo.",
        accept: "Por favor, escribe un valor con una extensión aceptada.",
        maxlength: jQuery.validator.format("Por favor, no escribas más de {0} caracteres."),
        minlength: jQuery.validator.format("Por favor, no escribas menos de {0} caracteres."),
        rangelength: jQuery.validator.format("Por favor, escribe un valor entre {0} y {1} caracteres."),
        range: jQuery.validator.format("Por favor, escribe un valor entre {0} y {1}."),
        max: jQuery.validator.format("Por favor, escribe un valor menor o igual a {0}."),
        min: jQuery.validator.format("Por favor, escribe un valor mayor o igual a {0}.")
    });
	
	
	
	//una peticion via AJAX 
	$("#forma-login").submit(function(e) {
		
		e.preventDefault();
		
	}).validate({
	rules: {
		correo: {
			required: true,
			maxlength: 100
		},
		contrasenia: {
			required: true
		}
	},
	errorPlacement: function(error, element) {
		error.appendTo(element.parent());
	},
	submitHandler: function(form) {
		
		var email = $("#correo").val();
		var password = $("#contrasenia").val();

		
		$.post("/usuario/login", { 'correo': email, 'contrasenia': password },  function( fragmento ) {

				var newDoc = document.open("text/html", "replace");
				newDoc.write(fragmento);
				newDoc.close();
			
	 			
		});
		
		return false;
	}

	});
	
	// registro de usuario 
	 $("#forma-registro").submit(function(e) {
		
		e.preventDefault();
		
	}).validate({
	rules: {
		correo: {
			required: true,
			maxlength: 100
		},
		contrasenia: {
			required: true
		},
		nombre: {
			required: true
			
		},
		apellidoPat:{
			required: true
		},
		apellidoMat:{
			required:true
		},
		matricula:{
			required:true
		}
	},
	errorPlacement: function(error, element) {
		error.appendTo(element.parent());
	},
	submitHandler: function(form) {
		
	var nombre = $("#nombre").val();
	var apellidopat = $("#apellidoPat").val();
	var apellidomat = $("#apellidoMat").val();
	var matricula = $("#matricula").val();
	var correo = $("#correo").val();
	var password = $("#contrasenia").val(); 
        
		
		$.get("/usuario/registro", {'nombre':nombre,'apellidoPat':apellidopat,'apellidoMat':apellidomat,'matricula':matricula,
	       'correo':correo, 'contrasenia':password },  function( fragmento ) {

				
				
				$('#modalMensaje').replaceWith(fragmento);
				
				var myModal = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalExitosoError'));
				myModal.show();
	 			
		});
		
		return false;
	}

	});
	
	
	//forma registrocurso
	$("#forma-registro-curso").submit(function(e) {
		
		e.preventDefault();
		
	}).validate({
	rules: {
		
		nombre: {
			required: true
			
		}
	},
	errorPlacement: function(error, element) {
		error.appendTo(element.parent());
	},
	submitHandler: function(form) {
		
	var nombre = $("#nombre").val();
	var descripcion =$("#descripcion").val();
        
		
		$.get("/curso/guardarcurso", {'nombre':nombre ,'descripcion':descripcion},  function( fragmento ) {

				
				$('#modalMensaje').replaceWith(fragmento);
				
				var myModalExample = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalGuardarCurso'));
				myModalExample.hide();
				var myModal = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalExitosoError'));
				myModal.show();
				
		});
		
		return false;
	}

	});
	
	
	//una peticion via AJAX 
	$("#agregar-actividad-forma").submit(function(e) {
		
		e.preventDefault();
		
	}).validate({
	rules: {
		descripcion: {
			required: true
			
		},
		objetivos: {
			required: true
			
		},
		puntosEvaluar:{
			required:true
		},
		puntaje:{
			required:true,
			digits:true
		}
		
	},
	errorPlacement: function(error, element) {
		error.appendTo(element.parent());
	},
	submitHandler: function(form) {
		
		var descripcion = $("#descripcion").val();
		var objetivos = $("#objetivos").val();
		var puntosEvaluar = $("#puntosEvaluar").val();
		var puntaje = $("#puntaje").val();

		
		$.post("/actividad/guardar", 
		{ 'descripcion': descripcion, 
		'objetivos': objetivos, 
		'puntosEvaluar': puntosEvaluar, 
		'puntaje': puntaje},  function( fragmento ) {

				
				$('#modalMensaje').replaceWith(fragmento);
				
				var myModalExample = bootstrap.Modal.getOrCreateInstance(document.querySelector('#exampleModal'));
				myModalExample.hide();
				var myModal = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalExitosoError'));
				myModal.show();
	 			
		});
		
		return false;
	}

	});
	
	// respuesta de actividad 
	 $("#forma-respuesta").submit(function(e) {
		
		e.preventDefault();
		
	}).validate({
	rules: {
		actividad: {
			required: true
		},
		github: {
			required: true
		}
	},
	errorPlacement: function(error, element) {
		error.appendTo(element.parent());
	},
	submitHandler: function(form) {
		
	var actividad = $("#actividad").val();
	var github = $("#github").val();
	var drive = $("#drive").val();
	 
        
		
		$.post("/respuesta/guardar", {'github':github,'drive':drive,'actividad':actividad},  function( fragmento ) {

				
				
				$('#modalMensaje').replaceWith(fragmento);
				
				var myModal = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalExitosoError'));
				myModal.show();
	 			
		});
		
		return false;
	}

	});
	
 // funciones 	
  function seleccionaActividad(){
	var id_actividad = $("seleccionarActividad").val();
	
	
	$.ajax ({
		 type: "GET",
		 url:"/curso/buscar",
		 beforeSend: function(objeto){
			
		},success:function(data){
			$("#actividad-select").html(data);
		}
	});
};
 
  obtenerCursos = function() {
	
		$.get("/curso/buscar", {}, function(fragmento){
			 	var newDoc = document.open("text/html", "replace");
				newDoc.write(fragmento);
				newDoc.close();
			
		});
	
	};
	obtenerActividades = function() {
	
		$.get("/actividad/buscar", {}, function(fragmento){
			 	var newDoc = document.open("text/html", "replace");
				newDoc.write(fragmento);
				newDoc.close();
			
		});
	
	};

	

    
   
   
    
    
   });
   
   
   
  
	

	