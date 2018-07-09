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
import br.com.techHouse.zmed.entity.MedicamentoInventario;
import br.com.techHouse.zmed.enums.ZmedMensagemEnum;
import br.com.techHouse.zmed.exception.MedicamentoNaoEncontradoException;
import br.com.techHouse.zmed.message.MensagemFactory;
import br.com.techHouse.zmed.to.MedicamentoInventarioTO;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@Stateless
public class MedicamentoInventarioDAO extends ZmedDataAbstract<MedicamentoInventario> {
	
	private @Inject MensagemFactory mensagemFactory;

	@Override
	public MedicamentoInventario recuperar(Serializable id) throws Exception {
		CriteriaQuery<MedicamentoInventario> criteria = getCriteriaBuilder().createQuery(MedicamentoInventario.class);
		Root<MedicamentoInventario> root = criteria.from(MedicamentoInventario.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public MedicamentoInventario recuperarCompleto(Integer id) throws Exception {
		CriteriaQuery<MedicamentoInventario> criteria = getCriteriaBuilder().createQuery(MedicamentoInventario.class);
		Root<MedicamentoInventario> root = criteria.from(MedicamentoInventario.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public MedicamentoInventario recuperarPorNome(String nome) throws MedicamentoNaoEncontradoException {
		try {
			CriteriaQuery<MedicamentoInventario> criteria = getCriteriaBuilder().createQuery(MedicamentoInventario.class);
			Root<MedicamentoInventario> root = criteria.from(MedicamentoInventario.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("nome"), nome))).getSingleResult();
		} catch (NoResultException e) {
			throw new MedicamentoNaoEncontradoException(mensagemFactory.getMensagem(ZmedMensagemEnum.UC_MEDICAMENTO_NAO_ENCONTRADO.getKey())+": "+nome);
		}
	}

	private Predicate[] comporFiltro(Root<MedicamentoInventario> root, MedicamentoInventarioTO medicamentoInventarioTO, CriteriaQuery<?> criteria) {
		List<Predicate> listaPredicate = new ArrayList<>();
		listaPredicate.add(comporFiltroPorDataExclusao(root));
		if (!UtilNullEmpty.isNullOrEmpty(medicamentoInventarioTO.getMedicamentoInventario().getId())) {
			listaPredicate.add(comporFiltroPorId(medicamentoInventarioTO.getMedicamentoInventario().getId(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(medicamentoInventarioTO.getMedicamentoInventario().getDataCadastro())) {
			listaPredicate.add(comporFiltroPorDataCadastro(medicamentoInventarioTO.getMedicamentoInventario().getDataCadastro(), root));
		}
		/*if (!UtilNullEmpty.isNullOrEmpty(medicamentoInventarioTO.getMedicamentoInventario().getNotaFiscal())) {
			listaPredicate.add(comporFiltroPorNome(medicamentoInventarioTO.getMedicamento().getApresentacao(), root));
		}*/
		return (Predicate[]) listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	/*private Predicate comporFiltroPorNome(String apresentacao, Root<MedicamentoInventario> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("apresentacao"))), "%"+apresentacao.toLowerCase()+"%");
	}*/
	
	private Predicate comporFiltroPorDataCadastro(Date dataCadastro, Root<MedicamentoInventario> root) {
		return getCriteriaBuilder().equal(root.<Timestamp>get("dataCadastro"), dataCadastro);
	}
	
	private Predicate comporFiltroPorId(Integer id, Root<MedicamentoInventario> root) {
		return getCriteriaBuilder().equal(root.<String>get("id"), id);
	}
	
	private Predicate comporFiltroPorDataExclusao(Root<MedicamentoInventario> root) {
		return getCriteriaBuilder().or(getCriteriaBuilder().isNull(root.<Date>get("dataExclusao")));
	}

	public List<MedicamentoInventario> pesquisar(MedicamentoInventarioTO medicamentoInventarioTO) {
		CriteriaQuery<MedicamentoInventario> criteria = getCriteriaBuilder().createQuery(MedicamentoInventario.class);
		Root<MedicamentoInventario> root = criteria.from(MedicamentoInventario.class);
		Predicate[] listaPredicate = comporFiltro(root, medicamentoInventarioTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void excluirMedicamentoInventario(MedicamentoInventario medicamentoInventario) {
		CriteriaUpdate<MedicamentoInventario> criteria = getCriteriaBuilder().createCriteriaUpdate(MedicamentoInventario.class);
		Root<MedicamentoInventario> root = criteria.from(MedicamentoInventario.class);
		criteria.set("dataExclusao", new Date());
		criteria.set("status", medicamentoInventario.getStatus());
		criteria.where(getCriteriaBuilder().equal(root.get("id"),medicamentoInventario.getId()));
		getManager().createQuery(criteria).executeUpdate();
	}
	
}