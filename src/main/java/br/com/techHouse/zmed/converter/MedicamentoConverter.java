package br.com.techHouse.zmed.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.techHouse.zmed.entity.Medicamento;
import br.com.techHouse.zmed.service.MedicamentoService;
import br.com.techHouse.zmed.util.EjbUtil;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@FacesConverter("medicamentoConverter")
public class MedicamentoConverter implements Converter {
	
	private MedicamentoService medicamentoService;

	public MedicamentoConverter() {
		if (medicamentoService == null) {
			try {
				EjbUtil<MedicamentoService> ejbUtil = new EjbUtil<MedicamentoService>();
				this.medicamentoService = ejbUtil.getService("MedicamentoService");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty()){
			try {
				return this.medicamentoService.recuperarCompletoPorId(Integer.valueOf(value));
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
			if (value instanceof Medicamento) {
				Medicamento medicamento = (Medicamento) value;
	            return medicamento.getId().toString();
	        } else {
	            throw new IllegalArgumentException("Cannot convert object");
	        }
		}
		return null;
	}

}