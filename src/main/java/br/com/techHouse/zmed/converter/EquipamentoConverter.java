package br.com.techHouse.zmed.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.techHouse.zmed.entity.Equipamento;
import br.com.techHouse.zmed.service.EquipamentoService;
import br.com.techHouse.zmed.util.EjbUtil;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@FacesConverter("equipamentoConverter")
public class EquipamentoConverter implements Converter {
	
	private EquipamentoService equipamentoService;

	public EquipamentoConverter() {
		if (equipamentoService == null) {
			try {
				EjbUtil<EquipamentoService> ejbUtil = new EjbUtil<EquipamentoService>();
				this.equipamentoService = ejbUtil.getService("EquipamentoService");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty()){
			try {
				return this.equipamentoService.recuperarCompletoPorId(Integer.valueOf(value));
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
			if (value instanceof Equipamento) {
				Equipamento equipamento = (Equipamento) value;
	            return equipamento.getId().toString();
	        } else {
	            throw new IllegalArgumentException("Cannot convert object");
	        }
		}
		return null;
	}

}