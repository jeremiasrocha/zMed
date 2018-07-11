package br.com.techHouse.zmed.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.techHouse.zmed.entity.Operadora;
import br.com.techHouse.zmed.service.OperadoraService;
import br.com.techHouse.zmed.util.EjbUtil;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@FacesConverter("operadoraConverter")
public class OperadoraConverter implements Converter {
	
	private OperadoraService operadoraService;

	public OperadoraConverter() {
		if (operadoraService == null) {
			try {
				EjbUtil<OperadoraService> ejbUtil = new EjbUtil<OperadoraService>();
				this.operadoraService = ejbUtil.getService("OperadoraService");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty()){
			try {
				return this.operadoraService.recuperarCompletoPorId(Integer.valueOf(value));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!UtilNullEmpty.isNullOrEmpty(value)) {
			if (value instanceof Operadora) {
				Operadora operadora = (Operadora) value;
	            return operadora.getId().toString();
	        } else {
	            throw new IllegalArgumentException("Cannot convert object");
	        }
		}
		return null;
	}

}