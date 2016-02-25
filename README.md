# meusprojetosmaven

TOMCAT_HOME="/home/alessandrots/desenvolvimento/java/tomcat7.0.68"
CATALINA_HOME="/home/alessandrots/desenvolvimento/java/tomcat7.0.68"
MAVEN_HOME="/home/alessandrots/desenvolvimento/java/maven3.3.9
ANT_HOME="/home/alessandrots/desenvolvimento/java/ant1.9.6"

$ANT_HOME/bin:$MAVEN_HOME/bin:

$ mvn archetype:generate -DgroupId=br.com.caelum.maven -DartifactId=produtos -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

$ mvn clean
$ mvn compile
$ mvn test
$ mvn package
$ java -cp target/produtos-1.0-SNAPSHOT.jar br.com.caelum.maven.App

para trabalhar dentro do eclipse
$ mvn eclipse:eclipse

para ver o q tem dentro do jar:
unzip -l target/produtos-1.0-SNAPSHOT.jar

Altere o seu arquivo pom.xml e adicione a dependência do XStream:
        <dependency>
            <groupId>com.thoughtworks.xstream</groupId>
            <artifactId>xstream</artifactId>
            <version>1.3.1</version>
        </dependency>
Gere novamente a configuração do projeto para o Eclipse. Note que o Eclipse ainda não compila seu projeto pois não encontra a variável M2_REPO. 
Vá em suas configurações (Preferences), escolha Classpath variables e adicione a variável M2_REPO apontando para o diretório de seu usuário, diretório .m2, diretório repository.

Tente compilar o seu projeto via linha de comando (compile). Quando o jar do XStream foi baixado?

***** 
Para startar o ECLIPSE: ./eclipse -clean -nosplash:
http://stackoverflow.com/questions/25666133/eclipse-cant-start-on-linux-java-fatal-error

************************************************************************************************

mvn -o test
(o 'o' é para executar de modo offline) e aí executa os testes

respositório central
http://mirrors.ibiblio.org/maven2/


************************************************************************************************

fiz mvn dependency:help e saiu o resultado:


dependency:analyze
  Analyzes the dependencies of this project and determines which are: used and
  declared; used and undeclared; unused and declared. This goal is intended to
  be used standalone, thus it always executes the test-compile phase - use the
  dependency:analyze-only goal instead when participating in the build
  lifecycle.
  By default, maven-dependency-analyzer is used to perform the analysis, with
  limitations due to the fact that it works at bytecode level, but any analyzer
  can be plugged in through analyzer parameter.

dependency:analyze-dep-mgt
  This mojo looks at the dependencies after final resolution and looks for
  mismatches in your dependencyManagement section. In versions of maven prior to
  2.0.6, it was possible to inherit versions that didn't match your
  dependencyManagement. See MNG-1577 for more info. This mojo is also useful for
  just detecting projects that override the dependencyManagement directly. Set
  ignoreDirect to false to detect these otherwise normal conditions.

dependency:analyze-duplicate
  Analyzes the <dependencies/> and <dependencyManagement/> tags in the pom.xml
  and determines the duplicate declared dependencies.

dependency:analyze-only
  Analyzes the dependencies of this project and determines which are: used and
  declared; used and undeclared; unused and declared. This goal is intended to
  be used in the build lifecycle, thus it assumes that the test-compile phase
  has been executed - use the dependency:analyze goal instead when running
  standalone.
  By default, maven-dependency-analyzer is used to perform the analysis, with
  limitations due to the fact that it works at bytecode level, but any analyzer
  can be plugged in through analyzer parameter.

dependency:analyze-report
  Analyzes the dependencies of this project and produces a report that
  summarizes which are: used and declared; used and undeclared; unused and
  declared.

dependency:build-classpath
  This goal will output a classpath string of dependencies from the local
  repository to a file or log.

dependency:copy
  Goal that copies a list of artifacts from the repository to defined locations.

dependency:copy-dependencies
  Goal that copies the project dependencies from the repository to a defined
  location.

dependency:get
  Resolves a single artifact, eventually transitively, from the specified remote
  repositories. Caveat: will always check the central repository defined in the
  super pom. You could use a mirror entry in your settings.xml

dependency:go-offline
  Goal that resolves all project dependencies, including plugins and reports and
  their dependencies.

dependency:help
  Display help information on maven-dependency-plugin.
  Call mvn dependency:help -Ddetail=true -Dgoal=<goal-name> to display parameter
  details.

dependency:list
  Displays the list of dependencies for this project.

dependency:list-repositories
  Goal that resolves all project dependencies and then lists the repositories
  used by the build and by the transitive dependencies

dependency:properties
  Goal that sets a property pointing to the artifact file for each project
  dependency. For each dependency (direct and transitive) a project property
  will be set which follows the groupId:artifactId:type:[classifier] form and
  contains the path to the resolved artifact.

dependency:purge-local-repository
  Remove the project dependencies from the local repository, and optionally
  re-resolve them.

dependency:resolve
  Goal that resolves the project dependencies from the repository.

dependency:resolve-plugins
  Goal that resolves all project plugins and reports and their dependencies.

dependency:sources
  Goal that resolves the project source dependencies from the repository.

dependency:tree
  Displays the dependency tree for this project.

dependency:unpack
  Goal that retrieves a list of artifacts from the repository and unpacks them
  in a defined location.

dependency:unpack-dependencies
  Goal that unpacks the project dependencies from the repository to a defined
  location.
  
************************************************************************************************
Para gerar um relatório de testes: 
$ mvn surefire-report:report  
$ mvn pmd:pmd

Usando o jacoco para ver a cobertua de código


Configuração do Jacoco:
<build>
    <plugins>
	<plugin>
	      <groupId>org.jacoco</groupId>
	      <artifactId>jacoco-maven-plugin</artifactId>
	      <version>0.6.3.201306030806</version>
	      <executions>
		      <execution>
			      <id>prepare-agent</id>
			      <goals>
				      <goal>prepare-agent</goal>
			      </goals>
			  </execution>
			  <execution>
			      <id>report</id>
			      <phase>prepare-package</phase>
			      <goals>
				      <goal>report</goal>
			      </goals>
		      </execution>
		      <execution>
			      <id>check</id>
			      <goals>
				      <goal>check</goal>
			      </goals>
			      <!--
			      <configuration>
				      <check>
					<classRatio>100</classRatio>
					<instructionRatio>90</instructionRatio>
					<methodRatio>95</methodRatio>
					<branchRatio>85</branchRatio>
					<complexityRatio>85</complexityRatio>
					<lineRatio>90</lineRatio>
				      </check>
			      </configuration>
			      -->
		      </execution>
		</executions>
	    </plugin>
    </plugins>
</build>
	
$ mvn package
abrir página target/site/jacoco/index.html	

Para conseguir definir uma checagem obrigatória dizendo o mínimo de cobertura desejado, é só descomentar o bloco de configuration acima:
<!--
  <configuration>
  </configuration>
-->  

Aí rodar:
$mvn verify

Como a checagem está acima de 85% é muito alta então vai falhar o build

*********************************
PROJETOS Web
Começamos nosso segundo projeto criando uma nova aplicação, mas desta vez web. 
A mudança ocorre ao utilizar um archetype de web e indicar o Maven que o Eclipse será configurado com suporte a projetos web:


mvn archetype:generate -DgroupId=br.com.caelum.maven -DartifactId=produtos-web -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
mvn eclipse:eclipse -Dwtpversion=2.0

mkdir src/main/java
mvn eclipse:eclipse -Dwtpversion=2.0

coloca a classe

package br.com.caelum.maven;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class BemvindoController {

    private Result result;

    public BemvindoController(Result result) {
        this.result = result;
    }

    @Path("/")
    public void bemvindo() {
        System.out.println("estou invocando o vraptor");
        result.use(Results.http()).body("<html>bem vindo!</html>");
    }

}

web.xml:
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
    version="3.0">
   <display-name>produtos-web</display-name>
</web-app>

Adiciona o build do jetty:
<build>
    <finalName>produtos-web</finalName>
    <plugins>
	<plugin>
	    <groupId>org.mortbay.jetty</groupId>
	    <artifactId>jetty-maven-plugin</artifactId>
	</plugin>
    </plugins>
</build>


E levantamos o jetty:

mvn jetty:run

para mudar a porta do jetty e configurar o Project Facet no maven (Maven => Update Project):
<build>
    <finalName>produtos-web</finalName>
    <plugins>
	<plugin>
	    <groupId>org.mortbay.jetty</groupId>
	    <artifactId>jetty-maven-plugin</artifactId>
	    <configuration>
				<scanIntervalSeconds>10</scanIntervalSeconds>
				<connectors>
				    <connector implementation="org.eclipse.jetty.server.nio.SelectChannelConnector">
				      <port>9090</port>
				      <maxIdleTime>60000</maxIdleTime>
				    </connector>
				  </connectors>
			    </configuration>
	</plugin>        
    
    <!-- http://stackoverflow.com/questions/7715260/java-compiler-level-does-not-match-the-version-of-the-installed-java-project-fac -->
		<plugin>
		  <artifactId>maven-compiler-plugin</artifactId>
		    <configuration>
		      <source>1.7</source>
		      <target>1.7</target>
		    </configuration>
		</plugin>
	    </plugins>
</build>
 

*********************************

# no diretorio do projeto web
mvn package

# no diretorio do projeto de produtos
mvn install


Adicionar a dependency no projeto web:
O que acontece se nossa aplicação web depender da biblioteca de produtos:

    <dependency>
      <groupId>br.com.caelum.maven</groupId>
      <artifactId>produtos</artifactId>
      <version>1.0-SNAPSHOT</version>
</dependency>

*********************************

Como fazer para ele se tornar opcional? Como fazer para excluir a dependência a biblioteca http? Podemos marcar ela como opcional:

<dependency>
    <groupId>org.apache.httpcomponents</groupId>
  <artifactId>httpclient</artifactId>
  <version>4.2.3</version>
  <optional>true</optional>
</dependency>

*********************************

Se a biblioteca fosse utilizada somente para os testes, usaríamos o escopo test, como foi feito com o junit.

Existem outros escopos como o de runtime que indica que a dependência só será necessária em tempo de execução, mas não durante a compilação/build. Essas dependências serão incluídas em um war, por exemplo, mas não utilizadas na compilação.

Vamos remover o opcional e adicionar o escopo de runtime:

<dependency>
    <groupId>org.apache.httpcomponents</groupId>
  <artifactId>httpclient</artifactId>
  <version>4.2.3</version>
  <scope>runtime</scope>
</dependency>

*********************************

O escopo padrão, compile, faz com que todas as dependências sejam utilizadas para compilar, testar e rodar sua aplicação. 
Por fim, o escopo provided é similar ao compile, mas não embute o jar na sua aplicação final, uma vez que seu servidor de aplicação 
ou ferramenta similar incluirá a dependência automaticamente. Já o escopo system permite que o atributo systemPath indique a posição de um jar. 
Esse escopo não é recomendado em geral pois faz com que tais bibliotecas não sejam gerenciadas pelo maven como todas as outras presentes no repositório central.



