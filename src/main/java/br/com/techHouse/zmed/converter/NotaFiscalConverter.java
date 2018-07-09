package br.com.techHouse.zmed.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.techHouse.zmed.entity.NotaFiscal;
import br.com.techHouse.zmed.service.NotaFiscalService;
import br.com.techHouse.zmed.util.EjbUtil;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@FacesConverter("notaFiscalConverter")
public class NotaFiscalConverter implements Converter {
	
	private NotaFiscalService notaFiscalService;

	public NotaFiscalConverter() {
		if (notaFiscalService == null) {
			try {
				EjbUtil<NotaFiscalService> ejbUtil = new EjbUtil<NotaFiscalService>();
				this.notaFiscalService = ejbUtil.getService("NotaFiscalService");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty()){
			try {
				return this.notaFiscalService.recuperarCompletoPorId(Integer.valueOf(value));
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
			if (value instanceof NotaFiscal) {
				NotaFiscal notaFiscal = (NotaFiscal) value;
	            return notaFiscal.getId().toString();
	        } else {
	            throw new IllegalArgumentException("Cannot convert object");
	        }
		}
		return null;
	}

}