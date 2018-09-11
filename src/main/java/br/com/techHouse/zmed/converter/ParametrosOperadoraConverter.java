package br.com.techHouse.zmed.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.techHouse.zmed.entity.ParametrosOperadora;
import br.com.techHouse.zmed.service.ParametrosOperadoraService;
import br.com.techHouse.zmed.util.EjbUtil;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@FacesConverter("parametrosOperadoraConverter")
public class ParametrosOperadoraConverter implements Converter {
	
	private ParametrosOperadoraService parametrosOperadoraService;

	public ParametrosOperadoraConverter() {
		if (parametrosOperadoraService == null) {
			try {
				EjbUtil<ParametrosOperadoraService> ejbUtil = new EjbUtil<ParametrosOperadoraService>();
				this.parametrosOperadoraService = ejbUtil.getService("ParametrosOperadoraService");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty()){
			try {
				return this.parametrosOperadoraService.recuperarCompletoPorId(Integer.valueOf(value));
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
			if (value instanceof ParametrosOperadora) {
				ParametrosOperadora parametrosOperadora = (ParametrosOperadora) value;
	            return parametrosOperadora.getId().toString();
	        } else {
	            throw new IllegalArgumentException("Cannot convert object");
	        }
		}
		return null;
	}

}