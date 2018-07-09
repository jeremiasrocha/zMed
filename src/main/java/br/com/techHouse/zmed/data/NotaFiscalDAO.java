package br.com.techHouse.zmed.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.techHouse.zmed.entity.NotaFiscal;
import br.com.techHouse.zmed.enums.ZmedMensagemEnum;
import br.com.techHouse.zmed.exception.NotaFiscalNaoEncontradaException;
import br.com.techHouse.zmed.message.MensagemFactory;
import br.com.techHouse.zmed.to.NotaFiscalTO;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@Stateless
public class NotaFiscalDAO extends ZmedDataAbstract<NotaFiscal> {
	
	private @Inject MensagemFactory mensagemFactory;

	@Override
	public NotaFiscal recuperar(Serializable id) throws Exception {
		CriteriaQuery<NotaFiscal> criteria = getCriteriaBuilder().createQuery(NotaFiscal.class);
		Root<NotaFiscal> root = criteria.from(NotaFiscal.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public NotaFiscal recuperarCompleto(Integer id) throws Exception {
		CriteriaQuery<NotaFiscal> criteria = getCriteriaBuilder().createQuery(NotaFiscal.class);
		Root<NotaFiscal> root = criteria.from(NotaFiscal.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public NotaFiscal recuperarPorNumero(Integer numero) throws NotaFiscalNaoEncontradaException {
		try {
			CriteriaQuery<NotaFiscal> criteria = getCriteriaBuilder().createQuery(NotaFiscal.class);
			Root<NotaFiscal> root = criteria.from(NotaFiscal.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("numero"), numero))).getSingleResult();
		} catch (NoResultException e) {
			throw new NotaFiscalNaoEncontradaException(mensagemFactory.getMensagem(ZmedMensagemEnum.UC_NOTA_FISCAL_NAO_ENCONTRADA.getKey())+": "+numero);
		}
	}

	private Predicate[] comporFiltro(Root<NotaFiscal> root, NotaFiscalTO notaFiscalTO, CriteriaQuery<?> criteria) {
		List<Predicate> listaPredicate = new ArrayList<>();
		listaPredicate.add(comporFiltroPorDataExclusao(root));
		if (!UtilNullEmpty.isNullOrEmpty(notaFiscalTO.getNotaFiscal().getId())) {
			listaPredicate.add(comporFiltroPorId(notaFiscalTO.getNotaFiscal().getId(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(notaFiscalTO.getNotaFiscal().getNumero())) {
			listaPredicate.add(comporFiltroPorNumero(notaFiscalTO.getNotaFiscal().getNumero().toString(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(notaFiscalTO.getNotaFiscal().getDataEmissao())) {
			listaPredicate.add(comporFiltroPorDataEmissao(notaFiscalTO.getNotaFiscal().getDataEmissao(), root));
		}
		return (Predicate[]) listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	private Predicate comporFiltroPorNumero(String numero, Root<NotaFiscal> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("numero"))), "%"+numero.toLowerCase()+"%");
	}
	
	private Predicate comporFiltroPorDataEmissao(Date dataEmissao, Root<NotaFiscal> root) {
		return getCriteriaBuilder().equal(root.<Timestamp>get("dataEmissao"), dataEmissao);
	}
	
	private Predicate comporFiltroPorId(Integer id, Root<NotaFiscal> root) {
		return getCriteriaBuilder().equal(root.<String>get("id"), id);
	}
	
	private Predicate comporFiltroPorDataExclusao(Root<NotaFiscal> root) {
		return getCriteriaBuilder().or(getCriteriaBuilder().isNull(root.<Date>get("dataExclusao")));
	}

	public List<NotaFiscal> pesquisar(NotaFiscalTO notaFiscalTO) {
		CriteriaQuery<NotaFiscal> criteria = getCriteriaBuilder().createQuery(NotaFiscal.class);
		Root<NotaFiscal> root = criteria.from(NotaFiscal.class);
		Predicate[] listaPredicate = comporFiltro(root, notaFiscalTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void excluirNotaFiscal(NotaFiscal notaFiscal) {
		CriteriaUpdate<NotaFiscal> criteria = getCriteriaBuilder().createCriteriaUpdate(NotaFiscal.class);
		Root<NotaFiscal> root = criteria.from(NotaFiscal.class);
		criteria.set("dataExclusao", new Date());
		criteria.set("status", notaFiscal.getStatus());
		criteria.where(getCriteriaBuilder().equal(root.get("id"),notaFiscal.getId()));
		getManager().createQuery(criteria).executeUpdate();
	}
	
}