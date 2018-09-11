package br.com.techHouse.zmed.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.techHouse.zmed.data.ParametrosOperadoraDAO;
import br.com.techHouse.zmed.data.ZmedDataAbstract;
import br.com.techHouse.zmed.entity.ParametrosOperadora;
import br.com.techHouse.zmed.to.FiltroTO;
import br.com.techHouse.zmed.to.ParametrosOperadoraTO;

@Stateless
public class ParametrosOperadoraService extends ServiceAbstract<ParametrosOperadora> {

	private @Inject ParametrosOperadoraDAO parametrosOperadoraDAO;

	@Override
	protected ZmedDataAbstract<ParametrosOperadora> getEntityBean() {
		return parametrosOperadoraDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ParametrosOperadora recuperarCompletoPorNome(String nome) throws Exception {
		ParametrosOperadora parametrosOperadora = parametrosOperadoraDAO.recuperarPorNome(nome);
		return parametrosOperadora;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ParametrosOperadora recuperarCompletoPorId(Integer id) throws Exception {
		ParametrosOperadora parametrosOperadora = parametrosOperadoraDAO.recuperarCompleto(id);
		return parametrosOperadora;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void paginar(FiltroTO<ParametrosOperadoraTO> filtro) throws Exception {
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ParametrosOperadora recuperarParametrosOperadora(Integer idOperadora) throws Exception {
		ParametrosOperadora parametrosOperadora = parametrosOperadoraDAO.recuperarParametrosOperadora(idOperadora);
		return parametrosOperadora;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ParametrosOperadora> pesquisar(ParametrosOperadoraTO parametrosOperadoraTO) throws Exception{
		List<ParametrosOperadora> listaParametrosOperadoraCompleta = parametrosOperadoraDAO.pesquisar(parametrosOperadoraTO);
		/*for (ParametrosOperadora parametrosOperadora : listaParametrosOperadoraCompleta) {
			parametrosOperadora.setOperadora(operadoraService.recuperarCompletoPorId(parametrosOperadora.getOperadora().getId()));
			listaParametrosOperadoraCompleta.add(parametrosOperadora);
		}*/
		return listaParametrosOperadoraCompleta;
	}

}