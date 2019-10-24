package maven_hibernate_2.maven_hibernate_2;

import org.junit.jupiter.api.Test;

import dao.DaoGeneric;
import model.Pessoa;

public class TesteHibernateUtil {
	
	DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	
	//@Test
	public void testeSalvar() {
		
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Clid Saniny");
		pessoa.setSobreNome("Robalo");
		pessoa.setLogin("clid");
		pessoa.setSenha("1234");
		pessoa.setEmail("clid@gmail.com");
		pessoa.setIdade(23);
		
		daoGeneric.salvar(pessoa);
	}
	
	//@Test
	public void testPesquisarPorObjecto() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(2L);
		pessoa = daoGeneric.pesquisarPorObject(pessoa);
		
		System.out.println(pessoa.toString());
	}
	
	@Test
	public void testPesquisarPorId() {
		Pessoa pessoa = new Pessoa();
		pessoa = daoGeneric.pesquisarPorId(1L, pessoa);
		
		System.out.println(pessoa.toString());
	}
	

}
