17_SpringMockitoTest
(1)@Captor, capture argument
(2)Mockitr verify, never(), times(), thenReturn()

25_SpringbootWebSecurity:
(1) logback.xml, Logger 
(2) Spring Security, InMemoryUserDetailsManager, Custom UserDetailsService
https://spring.io/guides/gs/securing-web/
https://stackoverflow.com/questions/49847791/java-spring-security-user-withdefaultpasswordencoder-is-deprecated/49847852
https://www.logicbig.com/tutorials/spring-framework/spring-security/user-details-service.html

26_SpringBootSecurityMySQL:
https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d
(1) Lombok annotations @Builder, @Data...
(2) javax.validation, @Size(), @Email, @NotEmpty...
(3) hybernate @ManyToMany, MySQL user, role, CASCADE
(4) thymeleaf-extras-springsecurity5, <div sec:authorize="hasRole('USER')">
(5) MyUserDetailsService with authorites
(6) UserService save user
(7) MyAuthenticationSuccessHandler to redirect different roles
(8) Unit test UserService, Mockito @Before, setUp
