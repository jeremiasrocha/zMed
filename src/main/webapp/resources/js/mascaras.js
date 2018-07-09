function mascara(o,f){
    v_obj=o;
    v_fun=f;
    var evt = window.event; 
	if(!evt){
		setTimeout("execmascara()",1);
	}else{
		if (evt.keyCode) { 
            tecla = evt.keyCode; 
		} else if (evt.which) { 
            tecla = evt.which; 
		}
		if(tecla != 8){
			setTimeout("execmascara()",1);
		}
		
	}	
}

function execmascara(){
	var tecla = ""; 
	
    v_obj.value=v_fun(v_obj.value);
}

function leech(v){
    v=v.replace(/o/gi,"0")
    v=v.replace(/i/gi,"1")
    v=v.replace(/z/gi,"2")
    v=v.replace(/e/gi,"3")
    v=v.replace(/a/gi,"4")
    v=v.replace(/s/gi,"5")
    v=v.replace(/t/gi,"7")
    return v;
}

function soNumeros(v){
    return v.replace(/\D/g,"")
}

function moeda(v){
	v = v.replace(/\D/g,"")                   //Remove tudo o que n�o � d�gito
	v = v.replace(/^(\d{3})(\d{3})(\d{2})/g,"$1.$2,$3") //Coloca par�nteses em volta dos dois primeiros d�gitos
	return v;
}

function maskMonetario(v){
    v=v.replace(/\D/g,"");
    v=v.replace(/(\d{2})$/,",$1");
    v=v.replace(/(\d+)(\d{3},\d{2})$/g,"$1.$2");
    var qtdLoop = (v.length-3)/3; var count = 0;
    while (qtdLoop > count){ count++;
    v=v.replace(/(\d+)(\d{3}.*)/,"$1.$2");
    }v=v.replace(/^(0)(\d)/g,"$2");
    return v; 
}

function valorComQuatroCasasDecimais(v){
    v=v.replace(/\D/g,"") // Remove tudo o que no dgito
    v=v.replace(/(\d)(\d{4})$/,"$1,$2") // Coloca virgula antes dos 4 ultimos
										// digitos
    return v
}

function valorComDuasCasasDecimais(v){
	   v=v.replace(/\D/g,"") // Remove tudo o que no dgito
	    v=v.replace(/(\d)(\d{2})$/,"$1.$2") // Coloca ponto antes dos 2 ultimos
											// digitos
	    return v
}

function telefone(v){
    v=v.replace(/\D/g,"")                 //Remove tudo o que não é dígito
    v=v.replace(/^(\d\d)(\d)/g,"($1) $2") //Coloca parânteses em volta dos dois primeiros dígitos
    v=v.replace(/(\d{4})(\d)/,"$1-$2")    //Coloca hífen entre o quarto e o quinto dígitos
    return v;
}

function telefoneNovo(v){
	v=v.replace(/\D/g,""); //Remove tudo o que não é dígito
	v=v.replace(/^(\d{2})(\d)/g,"($1) $2"); //Coloca parênteses em volta dos dois primeiros dígitos
	v=v.replace(/(\d)(\d{4})$/,"$1-$2"); //Coloca hífen entre o quarto e o quinto dígitos
	return v;
}

function cpf(v){
    v=v.replace(/\D/g,"")                    //Remove tudo o que n�o � d�gito
    v=v.replace(/(\d{3})(\d)/,"$1.$2")       //Coloca um ponto entre o terceiro e o quarto d�gitos
    v=v.replace(/(\d{3})(\d)/,"$1.$2")       //Coloca um ponto entre o terceiro e o quarto d�gitos
                                             //de novo (para o segundo bloco de n�meros)
    v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2") //Coloca um h�fen entre o terceiro e o quarto d�gitos
    return v;
}

function cep(v){
    v=v.replace(/\D/g,"")                //Remove tudo o que n�o � d�gito
    v=v.replace(/(\d{2})(\d)/,"$1.$2")  //Coloca um ponto entre o segundo e o terceiro d�gitos
    v=v.replace(/(\d{3})(\d)/,"$1-$2") 	//Coloca um tra�o entre o quinto e o sexto d�gitos
    return v;
}

function cnpj(v){
    v=v.replace(/\D/g,"")                           //Remove tudo o que n�o � d�gito
    v=v.replace(/^(\d{2})(\d)/,"$1.$2")             //Coloca ponto entre o segundo e o terceiro d�gitos
    v=v.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3") //Coloca ponto entre o quinto e o sexto d�gitos
    v=v.replace(/\.(\d{3})(\d)/,".$1/$2")           //Coloca uma barra entre o oitavo e o nono d�gitos
    v=v.replace(/(\d{4})(\d)/,"$1-$2")              //Coloca um h�fen depois do bloco de quatro d�gitos
    return v;
}

function data(v){
	v = v.replace(/\D/g,""); 						//Remove tudo o que n�o � d�gito
	v = v.replace(/^(\d{4})/gi,"$1/"); 				//Coloca uma barra entre o quarto e o quinto d�gito
	v = v.replace(/^(\d{2})/gi,"$1/");				//Coloca uma barra entre o segundo e o terceiro d�gito

    return v.substring(0,10);
}

function hora(v){
	v = v.replace(/\D/g,""); 				//Remove tudo o que n�o � d�gito
	v = v.replace(/^(\d{2})/gi,"$1:");		//Coloca : entre o segundo e o terceiro d�gito
	
	return v.substring(0,5);
}

function pispasep(v){
    v=v.replace(/\D/g,"")                    //Remove tudo o que n�o � d�gito
    v=v.replace(/(\d{3})(\d)/,"$1.$2")       //Coloca um ponto entre o terceiro e o quarto d�gitos
    v=v.replace(/(\d{5})(\d)/,"$1.$2")       //Coloca um ponto entre o quinto e o sexo d�gito
                                             //de novo (para o segundo bloco de n�meros)
    v=v.replace(/(\d{2})(\d{1,2})$/,"$1-$2") //Coloca um h�fen entre o terceiro e o quarto d�gitos
    return v;
}

function cnae(v){
    v=v.replace(/\D/g,"")                    //Remove tudo o que n�o � d�gito
    v=v.replace(/(\d{4})(\d)/,"$1-$2")       //Coloca um h�fen entre o quarto e o quinto d�gito
    v=v.replace(/(\d{1})(\d{1,2})$/,"$1/$2") //Coloca uma barra entre o terceiro e o quarto d�gitos
    return v;
}

function rde(v){
	s = v.substr(0,2).replace(/\W/, ""); 	// Pega s� os 2 primeiros caracteres e permite apenas de A-Z ou n�mero.
	s = s + v.substr(2).replace(/\D/g,"");	// Pega todos os caracteres depois do segundo e permite apenas num�ricos.
	v = s.replace(/^(\w{3})(\w{5})(\w{2})/gi,"$1/$2/$3"); // Mascara AA9/99999/999999.

	return v;
}

function cbo(v){
	v=v.replace(/\D/g,"")                    //Remove tudo o que n�o � d�gito
	v=v.replace(/(\d{1})(\d{1,2})$/,"$1-$2") //Coloca um h�fen entre o terceiro e o quarto d�gitos
 
	return v;
}

function quantidade(v) {
	v = v.replace(/\D/g,"")                	//Remove tudo o que n�o � d�gito
	v=v.replace(/(\d{3})(\d{3})/,"$1.$2")  	//Coloca um ponto entre o sexto e o terceiro d�gitos
	v=v.replace(/(\d{2})(\d{3})/,"$1.$2") 	//Coloca um ponto entre o quinto e o terceiro d�gitos
	v=v.replace(/(\d)(\d{3})/,"$1.$2")    	//Coloca um ponto entre o terceiro e o quarto d�gitos
	return v;
}

function fatorRedAcresc(v) {
	v = v.replace(/\D/g,"")                	//Remove tudo o que n�o � d�gito
	v=v.replace(/(\d)(\d{2})$/,"$1.$2");    //Coloca ponto antes dos dois ultimos digitos
	return v;
}

function contratoCambio(v) {	
	s = v.substr(0,2).replace(/\W/, ""); 	// Pega s� os 2 primeiros caracteres e permite apenas de A-Z ou n�mero.
	s = s + v.substr(2).replace(/\D/g,"");	// Pega todos os caracteres depois do segundo e permite apenas num�ricos.
	v = s.replace(/^(\w{2})(\w{6})/gi,"$1/$2"); // Mascara AA/999999
	return v;
}

function processo(v){
    v=v.replace(/\D/g,"")                           //Remove tudo o que n�o � d�gito
    v=v.replace(/^(\d{5})(\d)/,"$1.$2")             //Coloca ponto entre o segundo e o terceiro d�gitos
    v=v.replace(/\.(\d{6})(\d)/,".$1/$2")           //Coloca uma barra entre o oitavo e o nono d�gitos
    v=v.replace(/\/(\d{4})(\d)/,"/$1-$2")              //Coloca um h�fen depois do bloco de quatro d�gitos
    return v;
}
function rg(v){
    v=v.replace(/\D/g,""); //Substituí o que não é dígito por "", /g é [Global][1]
    v=v.replace(/(\d{1,2})(\d{3})(\d{3})(\d{1})$/,"$1.$2.$3-$4"); 
    // \d{1,2} = Separa 1 grupo de 1 ou 2 carac. (\d{3}) = Separa 1 grupo de 3 carac. (\d{1}) = Separa o grupo de 1 carac.
    // "$1.$2.$3-$4" = recupera os grupos e adiciona "." após cada.

    return v
}

function retiraEspacos(v) {
	v = v.replace(/\s+/g, '');
	return v;
}