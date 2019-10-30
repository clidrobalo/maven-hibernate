package maven_hibernate_2.maven_hibernate_2;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;

import dao.DaoGeneric;
import model.Pessoa;
import model.Telefone;

public class TesteHibernateUtil {
	
	DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	DaoGeneric<Telefone> daoGenericTelefone = new DaoGeneric<Telefone>();
	
	//@Test
	public void testeSalvar() {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Pedro Carlos");
		pessoa.setSobreNome("Lopes Dias");
		pessoa.setLogin("pedro");
		pessoa.setSenha("1234");
		pessoa.setEmail("pedro@gmail.com");
		pessoa.setIdade(32);
		
		daoGeneric.salvar(pessoa);
		
		//Criar regito telefone
		Telefone telefone1 = new Telefone();
		telefone1.setNumero("123456");
		telefone1.setTipo("Movel");
		telefone1.setPessoa(pessoa);
		daoGenericTelefone.salvar(telefone1);
		
		Telefone telefone2 = new Telefone();
		telefone2.setNumero("987458");
		telefone2.setTipo("Casa");
		telefone2.setPessoa(pessoa);
		daoGenericTelefone.salvar(telefone2);
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
	//@Test
	public void testeQueryListMaxResult() {
		System.out.println("|------- Teste Query com maxResult ------------|");
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		List<Pessoa> pessoas = daoGeneric.getEntityManager().createQuery("from Pessoa order by sobrenome")
				.setMaxResults(1)
				.getResultList();
	
		for(Pessoa pessoa: pessoas) {
			System.out.println(pessoa.toString());
		}
	
	}
	
	//Query realizado fora do daoGeneric
		@Test
		public void testeQueryComParametro() {
			System.out.println("|------- Teste Query Com parametro ------------|");
			DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
			List<Pessoa> pessoas = daoGeneric.getEntityManager().createQuery("from Pessoa where nome = :nome")
					.setParameter("nome", "Pedro Carlos")
					.getResultList();
		
			for(Pessoa pessoa: pessoas) {
				System.out.println(pessoa.toString());
			}
		
		}
		
		//Query realizado fora do daoGeneric
		//@Test
		public void testeQueryRetornandoSomaIdade() {
			System.out.println("|------- Teste Query Com parametro ------------|");
			DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
			Long somaIdades = (Long) daoGeneric.getEntityManager().createQuery("select sum(p.idade) from Pessoa p")
					.getSingleResult();
		
			System.out.println("Soma Idades: " + somaIdades);
		}
		
		//@Test
		public void testeNamedQuery() {
			System.out.println("|------- Teste Query com NamedQuery na Classe Pessoa ------------|");
			DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
			
			List<Pessoa> pessoas = daoGeneric.getEntityManager().createNamedQuery("Pessoa.all").getResultList();
			
			for(Pessoa pessoa: pessoas) {
				System.out.println(pessoa.toString());
			}
			
		}


}
