# Exemplo Spring

Aplicação simples desenvolvida para pratica dos conceitos do Spring Framework.
A intenção é que ela sirva de referência para outros projetos que adotem esta tecnologia.

Trabalha com autenticação e autorização, customização de componentes de validação e conversão, caches de resultados, 
content negotiation e diferentes ViewResolvers, processamento assíncrono, simulação de integração com sistema externo e teste de integração.

Para verificar o passo a passo de como foi feito o desenvolvido basta avaliar o histórico do git.
Trata-se da simulação de cadastro e compra de livros online.

## Backlog

1. Serviço de acesso ao sistema

	* Realizar acesso através das credencias de um banco de dados

2. Serviço de gerenciamento dos produtos

	* Cadastro de livro
	* Exibição dos livros

3. Serviço de gerenciamento da compra

	* Adicionar livros ao carrinho
	* Realizar o pagamento
	
## Acesso

A autenticação é feita pelo Spring Security utilizando o algoritmo de hash BCrypt.

Definiu-se algumas regras de acesso. 

Desta forma é necessário realizar algumas inserções no banco. O banco utilizado é o PostgreSQL 9.5

Exemplo:

	insert into role VALUES ('ROLE_ADMIN'), ('ROLE_COMPRADOR');
	insert into systemuser values ( 'admin', 'Administrador do Sistema', crypt('admin', gen_salt('bf')));	
	insert into systemuser values ( 'cliente', 'Cliente do Sistema', crypt('cliente', gen_salt('bf')));
	insert INTO systemuser_role values ('admin','ROLE_ADMIN');
	insert INTO systemuser_role values ('cliente','ROLE_COMPRADOR');
	
URL:

	Lista de produtos: http://localhost:8080/exemplo-spring/produtos
	Lista de produtos Json: http://localhost:8080/exemplo-spring/produtos.json
	Cadastro de Produtos: http://localhost:8080/exemplo-spring/produtos/form
	Finalizar compra: http://localhost:8080/exemplo-spring/shopping
	Logout: http://localhost:8080/exemplo-spring/logout
	
## Referências

	https://spring.io/docs
	Spring MVC - Domine o principal framework web Java - Alberto Souza
	Vire o jogo com Spring Framework - Henrique Lobo Weissmann
