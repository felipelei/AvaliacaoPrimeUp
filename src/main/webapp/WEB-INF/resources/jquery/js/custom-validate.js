jQuery.validator.addMethod("validarDataBR", function(value, element) {
        return this.optional(element) || moment(value,"DD/MM/YYYY").isValid();
    }, "Data incorreta.");

jQuery.validator.addMethod("dataMaiorIgual", function(value, element, param) {
		if(this.optional(element))
			return true;
		
		if(moment(value,"DD/MM/YYYY").isValid() && moment(param,"DD/MM/YYYY").isValid()) {
			var dataFinal = moment(value,"DD/MM/YYYY");
			var dataInicial = moment(param,"DD/MM/YYYY");
			
			return dataFinal.isAfter(dataInicial);
		}
		
		return false;
}, "A data termino nao pode ser menor que a data inicial.");