package br.com.unifacisa.pp.gym;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {
	
	public static void main(String[] args) {
		
		SpringApplication.run(App.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		Endereco end = new Endereco("Liberdade", "Martins Junior", "Campina Grande",568 , 58414070);
		
		
		Aluno p = new Aluno(null,"Pedro","pedro@gmail.com","12345678910", end, new ArrayList<Atividade>());
		
		end.setPessoaId(p);
		
		Endereco end2 = new Endereco("Monte Castelo", "Jose Carolino de lima", "Campina Grande", 409, 12345678);
		
		Categoria cat = new Categoria();
		cat.setNome("Aerobica");
		cat.setSalario(100.00);
		
		
		
		Funcionario pf =new Funcionario(null,"Lucas","lucas@gmail.com","10987654321",end2, new ArrayList<Atividade>(), cat);
		cat.setProfessor(pf);
		
		endRep.save(end);
		endRep.save(end2);
		
		catRep.save(cat);
		
		peRep.save(p);
		peRep.save(pf);
		
		*/
	}
}
