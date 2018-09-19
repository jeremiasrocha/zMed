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
import br.com.techHouse.zmed.entity.EquipamentoInventario;
import br.com.techHouse.zmed.enums.ZmedMensagemEnum;
import br.com.techHouse.zmed.exception.EquipamentoInventarioNaoEncontradoException;
import br.com.techHouse.zmed.message.MensagemFactory;
import br.com.techHouse.zmed.to.EquipamentoInventarioTO;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@Stateless
public class EquipamentoInventarioDAO extends ZmedDataAbstract<EquipamentoInventario> {
	
	private @Inject MensagemFactory mensagemFactory;

	@Override
	public EquipamentoInventario recuperar(Serializable id) throws Exception {
		CriteriaQuery<EquipamentoInventario> criteria = getCriteriaBuilder().createQuery(EquipamentoInventario.class);
		Root<EquipamentoInventario> root = criteria.from(EquipamentoInventario.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public EquipamentoInventario recuperarCompleto(Integer id) throws Exception {
		CriteriaQuery<EquipamentoInventario> criteria = getCriteriaBuilder().createQuery(EquipamentoInventario.class);
		Root<EquipamentoInventario> root = criteria.from(EquipamentoInventario.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public EquipamentoInventario recuperarPorNome(String nome) throws EquipamentoInventarioNaoEncontradoException {
		try {
			CriteriaQuery<EquipamentoInventario> criteria = getCriteriaBuilder().createQuery(EquipamentoInventario.class);
			Root<EquipamentoInventario> root = criteria.from(EquipamentoInventario.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("nome"), nome))).getSingleResult();
		} catch (NoResultException e) {
			throw new EquipamentoInventarioNaoEncontradoException(mensagemFactory.getMensagem(ZmedMensagemEnum.UC_EQUIPAMENTO_INVENTARIO_NAO_ENCONTRADO.getKey())+": "+nome);
		}
	}

	private Predicate[] comporFiltro(Root<EquipamentoInventario> root, EquipamentoInventarioTO equipamentoInventarioTO, CriteriaQuery<?> criteria) {
		List<Predicate> listaPredicate = new ArrayList<>();
		listaPredicate.add(comporFiltroPorDataExclusao(root));
		if (!UtilNullEmpty.isNullOrEmpty(equipamentoInventarioTO.getEquipamentoInventario().getId())) {
			listaPredicate.add(comporFiltroPorId(equipamentoInventarioTO.getEquipamentoInventario().getId(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(equipamentoInventarioTO.getEquipamentoInventario().getDataCadastro())) {
			listaPredicate.add(comporFiltroPorDataCadastro(equipamentoInventarioTO.getEquipamentoInventario().getDataCadastro(), root));
		}
		/*if (!UtilNullEmpty.isNullOrEmpty(equipamentoInventarioTO.getEquipamentoInventario().getNotaFiscal())) {
			listaPredicate.add(comporFiltroPorNome(equipamentoInventarioTO.getMedicamento().getApresentacao(), root));
		}*/
		return (Predicate[]) listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	/*private Predicate comporFiltroPorNome(String apresentacao, Root<EquipamentoInventario> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("apresentacao"))), "%"+apresentacao.toLowerCase()+"%");
	}*/
	
	private Predicate comporFiltroPorDataCadastro(Date dataCadastro, Root<EquipamentoInventario> root) {
		return getCriteriaBuilder().equal(root.<Timestamp>get("dataCadastro"), dataCadastro);
	}
	
	private Predicate comporFiltroPorId(Integer id, Root<EquipamentoInventario> root) {
		return getCriteriaBuilder().equal(root.<String>get("id"), id);
	}
	
	private Predicate comporFiltroPorDataExclusao(Root<EquipamentoInventario> root) {
		return getCriteriaBuilder().or(getCriteriaBuilder().isNull(root.<Date>get("dataExclusao")));
	}

	public List<EquipamentoInventario> pesquisar(EquipamentoInventarioTO equipamentoInventarioTO) {
		CriteriaQuery<EquipamentoInventario> criteria = getCriteriaBuilder().createQuery(EquipamentoInventario.class);
		Root<EquipamentoInventario> root = criteria.from(EquipamentoInventario.class);
		Predicate[] listaPredicate = comporFiltro(root, equipamentoInventarioTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void excluirEquipamentoInventario(EquipamentoInventario equipamentoInventario) {
		CriteriaUpdate<EquipamentoInventario> criteria = getCriteriaBuilder().createCriteriaUpdate(EquipamentoInventario.class);
		Root<EquipamentoInventario> root = criteria.from(EquipamentoInventario.class);
		criteria.set("dataExclusao", new Date());
		criteria.set("status", equipamentoInventario.getStatus());
		criteria.where(getCriteriaBuilder().equal(root.get("id"),equipamentoInventario.getId()));
		getManager().createQuery(criteria).executeUpdate();
	}
	
}