package br.com.techHouse.zmed.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import br.com.techHouse.zmed.entity.Medicamento;
import br.com.techHouse.zmed.service.MedicamentoService;
import br.com.techHouse.zmed.to.MedicamentoTO;

@Path("/medicamento")
@RequestScoped
public class MedicamentoRestService {
	
	private @Inject MedicamentoService medicamentoService;

	@GET
	@Path("/pesquisar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Medicamento> pesquisar(@QueryParam("term") String query) throws Exception {
		MedicamentoTO medicamentoTO = new MedicamentoTO();
		medicamentoTO.setMedicamento(new Medicamento());
		medicamentoTO.getMedicamento().setApresentacao(query);
		medicamentoTO.setMedicamentos(medicamentoService.pesquisarPorApresentacao(query));
		return medicamentoService.pesquisarPorApresentacao(query);
	}

}