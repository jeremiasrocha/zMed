package br.com.techHouse.zmed.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.techHouse.zmed.data.ProfissionalDAO;
import br.com.techHouse.zmed.data.ZmedDataAbstract;
import br.com.techHouse.zmed.entity.Profissional;
import br.com.techHouse.zmed.to.FiltroTO;
import br.com.techHouse.zmed.to.ProfissionalTO;

@Stateless
public class ProfissionalService extends ServiceAbstract<Profissional> {

	private @Inject ProfissionalDAO profissionalDAO;

	@Override
	protected ZmedDataAbstract<Profissional> getEntityBean() {
		return profissionalDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Profissional recuperarCompletoPorNome(String nome) throws Exception {
		Profissional profissional = profissionalDAO.recuperarPorNome(nome);
		return profissional;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Profissional recuperarCompletoPorId(Integer id) throws Exception {
		Profissional profissional = profissionalDAO.recuperarCompleto(id);
		return profissional;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void paginar(FiltroTO<ProfissionalTO> filtro) throws Exception {
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Profissional> pesquisar(ProfissionalTO profissionalTO) throws Exception{
		return profissionalDAO.pesquisar(profissionalTO);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirProfissional(Profissional profissional) throws Exception {
		profissionalDAO.excluirProfissional(profissional);
	}
	
}