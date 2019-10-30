package maven_hibernate_2.maven_hibernate_2;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
	
	//@Test
	public void testPesquisarPorId() {
		Pessoa pessoa = new Pessoa();
		pessoa = daoGeneric.pesquisarPorId(1L, pessoa);
		
		System.out.println(pessoa.toString());
	}
	
	//@Test
	public void testUpdateMerge() {
		Pessoa pessoa = new Pessoa();
		pessoa = daoGeneric.pesquisarPorId(2L, pessoa);
		
		//Atualizar dados
		pessoa.setNome("Neida Cristina");
		pessoa.setSobreNome("Fernandes");
		pessoa.setIdade(28);
		pessoa.setEmail("neida@gmail.com");
		pessoa.setLogin("Neida Fernandes");
		pessoa.setSenha("1234Neida");
		
		pessoa = daoGeneric.updateMerge(pessoa);
		
		System.out.println(pessoa.toString());
	}

	//@Test
	public void testeDelete() {
		Pessoa pessoa = new Pessoa();
		
		daoGeneric.deletePorId(4L, pessoa);
	}
	
	//@Test 
	public void listar() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas = daoGeneric.listar(Pessoa.class);
		
		for(Pessoa pessoa : pessoas) {
			System.out.println(pessoa.toString());
			System.out.println("------------------------------------------");
		}
	}
	
	//Query realizado fora do daoGeneric
	//@Test
	public void testeQueryList() {
		System.out.println("|------- Teste Query ------------|");
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		List<Pessoa> pessoas = daoGeneric.getEntityManager().createQuery("from Pessoa").getResultList();
	
		for(Pessoa pessoa: pessoas) {
			System.out.println(pessoa.toString());
		}
	
	}
	
	//Query realizado fora do daoGeneric
	@Test
	public void testeQueryListMaxResult() {
		System.out.println("|------- Teste Query ------------|");
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		List<Pessoa> pessoas = daoGeneric.getEntityManager().createQuery("from Pessoa order by sobrenome")
				.setMaxResults(1)
				.getResultList();
	
		for(Pessoa pessoa: pessoas) {
			System.out.println(pessoa.toString());
		}
	
	}

}
