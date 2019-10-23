package maven_hibernate_2.maven_hibernate_2;

import org.junit.jupiter.api.Test;

import dao.DaoGeneric;
import model.Pessoa;

public class TesteHibernateUtil {
	
	@Test
	public void testeHibernateUtil() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Clid Saniny");
		pessoa.setSobreNome("Robalo");
		pessoa.setLogin("clid");
		pessoa.setSenha("1234");
		pessoa.setEmail("clid@gmail.com");
		pessoa.setIdade(23);
		
		daoGeneric.salvar(pessoa);
	}
	

}
