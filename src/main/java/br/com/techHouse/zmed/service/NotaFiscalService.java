package br.com.techHouse.zmed.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.techHouse.zmed.data.NotaFiscalDAO;
import br.com.techHouse.zmed.data.ZmedDataAbstract;
import br.com.techHouse.zmed.entity.NotaFiscal;
import br.com.techHouse.zmed.to.FiltroTO;
import br.com.techHouse.zmed.to.NotaFiscalTO;

@Stateless
public class NotaFiscalService extends ServiceAbstract<NotaFiscal> {

	private @Inject NotaFiscalDAO notaFiscalDAO;

	@Override
	protected ZmedDataAbstract<NotaFiscal> getEntityBean() {
		return notaFiscalDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public NotaFiscal recuperarCompletoPorNumero(Integer numero) throws Exception {
		NotaFiscal notaFiscal = notaFiscalDAO.recuperarPorNumero(numero);
		return notaFiscal;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public NotaFiscal recuperarCompletoPorId(Integer id) throws Exception {
		NotaFiscal notaFiscal = notaFiscalDAO.recuperarCompleto(id);
		return notaFiscal;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void paginar(FiltroTO<NotaFiscalTO> filtro) throws Exception {
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<NotaFiscal> pesquisar(NotaFiscalTO notaFiscalTO) throws Exception{
		return notaFiscalDAO.pesquisar(notaFiscalTO);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirNotaFiscal(NotaFiscal notaFiscal) throws Exception {
		notaFiscalDAO.excluirNotaFiscal(notaFiscal);
	}

}