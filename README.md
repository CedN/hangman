# Hangman
This project is a Rest API to play Hangman. This context must allow me to try to realize a side project by using the clean architecture, the contract first development and TDD with 100% code coverage. It's an ambitious target, but I like to think it's a success :)

## Table of content
1. [Clean Architecture](#clean-architectire)
1. [Some Word about Presenter](#some-word-about-presenter)
1. [Implementation with Spring Boot](#implementation-with-spring-boot)
1. [Contract First Development](#contract-first-development)
1. [TDD](#tdd)
1. [Code Coverage](#code-coverage)
1. [Install, build and run](#install-build-run)
1. [Links](#links)
1. [Status](#status)

<a name="clean-arcitecture"></a>
## Clean Architecture

The Clean Architecture is defined by Robert C. Martin (aka Uncle Bob), and I advise you to read his blog article about it: https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html. My implementation modelizes the controller and the presenter as 2 different classes outside of Spring Boot framework classes. In the examples, we could find, modelizes the presenter as the return type of the controller call. This is a mistake, and I advise you to read this detailed explication by Jeremiah Flaga: https://softwareengineering.stackexchange.com/questions/357052/clean-architecture-use-case-containing-the-presenter-or-returning-data. Another mistake is to believe that the controller is a framework 'controller' class like in all MVC framework. Another article by Tobias Strandberg to understand this controller confusion: https://adevelopersdiscourse.blogspot.com/2020/06/clean-architecture-demystified.html.

<a name="some-word-about-presenter"></a>
### Some words about Presenter
Using a presenter as a class instead of a return value from controller calls allows to have non-homogeneous returns. In the case of a letter proposal, in the hangman game, the result can be a letter found, a wrong letter, the word to guess discovered or a game over. Having a homogeneous type as a return value that covers all of these possibilities is obviously difficult. And this type may be complex and not respect the SOLID principles. The case of the result of the proposed letter can be considered as one event among several possible. The use case calls the presenter to use the right method that matches the suitable event.

_The 'output boundary' of letter proposal that the presenter must implement:_
```java
public interface LetterProposalOutputBoundary {
	void gameInProgress(ProgressingGame letterProposalResult);
	void lostGame(LostGame lostGame);
	void wonGame(WonGame wonGame);
	void gameIsOver(GameOver gameOver);
}
```
<a name="implementation-with-spring-boot"></a>
### Implementation with Spring Boot
Controller and presenter are adapter classes. Controller calls the interactor ('usecase' in my project's terminology) through the InputBoundary interface. Usecase calls the presenter through the OutputBoundary. It provides the result of the processing to the Presenter. The presenter transforms the result into a DTO. Finally, the Rest controller calls the controller to process the action and then reads the response from the presenter.
![Implementation diagram](images/adapters-usage.png)

The presenter is stateful because it stores the DTO but the Rest Controller is stateless. I choose to provide controller and presenter at the instantiation of the Rest controller. Just annotate the presenter with `@RequestScope` allows to solve this problem.

_Example from [cna.apps.hangman.tech.ApplicationConfiguration](https://github.com/CedN/hangman/blob/f180d630b1a8e6e6a037c6a9d295fef728552771/hangman-api/src/main/java/cna/apps/hangman/tech/ApplicationConfiguration.java#L42-L46)_:
```java
@Bean
@RequestScope
public LetterProposedPresenter getLetterProposalOutputBoundary() {
  return new LetterProposedPresenter();
}
```
<a name="contract-first-development"></a>
## Contract First Development
Contract-first development is about defining the contract of an API before starting to implement it. Using code generation for server and clients from the contract guarantees they are aligned. We can also mock the API very easily during its development.

I use [OpenAPI 3.0.1](https://spec.openapis.org/oas/v3.0.1) to describe the contract of the Hangman API in a dedicated Maven module named 'specifications'. The 'openapi-generator-maven-plugin' allows to generate code. I want to generate only interfaces for the service side and change some package's names.

_Openapi generator maven plugin configuration for Spring server_:
```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.openapitools</groupId>
      <artifactId>openapi-generator-maven-plugin</artifactId>
      <!-- RELEASE_VERSION -->
      <version>5.1.1</version>
      <!-- /RELEASE_VERSION -->
      <executions>
        <execution>
          <goals>
            <goal>generate</goal>
          </goals>
          <configuration>
            <inputSpec>${project.basedir}/src/main/resources/hangman-openapi.yaml</inputSpec>
            <generatorName>spring</generatorName>
            <configOptions>
              <interfaceOnly>true</interfaceOnly>
              <basePackage>cna.apps.hangman.api</basePackage>
              <apiPackage>cna.apps.hangman.api</apiPackage>
              <modelPackage>cna.apps.hangman.api</modelPackage>
            </configOptions>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

I use [Random Words API](https://github.com/mcnaveen/Random-Words-API) to generate words to guess. I write the contract of the operation call and generate the client side in native Java by using 'openapi-generator-maven-plugin'

_Openapi generator maven plugin configuration for Java client_:
```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.openapitools</groupId>
      <artifactId>openapi-generator-maven-plugin</artifactId>
      <version>5.1.0</version>
      <executions>
        <execution>
          <goals>
            <goal>generate</goal>
          </goals>
          <configuration>
            <inputSpec>${project.basedir}/src/main/resources/random-words-openapi.yaml</inputSpec>
            <generatorName>java</generatorName>
            <configOptions>
              <apiPackage>cna.apps.hangman.apiclients.randomwords</apiPackage>
              <dateLibrary>java8</dateLibrary>
              <fullJavaUtil>true</fullJavaUtil>
              <library>native</library>
              <modelPackage>cna.apps.hangman.apiclients.randomwords.model</modelPackage>
              <openApiNullable>false</openApiNullable>
              <swaggerAnnotations>false</swaggerAnnotations>
            </configOptions>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```
<a name="tdd"></a>
## TDD
I start by implementing the use case tests. The progression into these unit tests allows me to design the domain entities. Coverage of use case tests allows the domain entities to emerge naturally. Then, I realize the development of adapters and then the Spring Boot glue.

The implementation of the domain (user case and entities) is realized by using outside-in TDD. However, the complete application is developed from an inside-out TDD angle.

No mock frameworks are used, so fake and spy classes are coded to simulate external dependencies behaviour or to assert external dependency calls.
<a name="code-coverage"></a>
## Code coverage
I coupled Jacoco and the [Coveralls](https://coveralls.io) service to measure code coverage. To achieve 100% code coverage, I excluded Spring Boot technical classes like application and configuration classes. 
<a name="install-build-run"></a>
## Install, build and run
* Prerequisite: have Java 16 installed (openJDK or Oracle JDK)
* Clone the repo
* Build the project with `mvnw clean package` from a terminal
* Run the class `cna.apps.hangman.tech.HangmanApplication` from your IDE or with `java -jar hangman-api/target/hangman-api-0.0.1-SNAPSHOT.jar` from a terminal. 
* Use [the OpenAPI contract](https://raw.githubusercontent.com/CedN/hangman/main/specifications/src/main/resources/hangman-openapi.yaml) to play :)
<a name="links"></a>
## Links
About clean architecture:
* Robert C. Martin's blog: https://blog.cleancoder.com/
* Tobias Strandberg's blog: https://adevelopersdiscourse.blogspot.com/
* Jeremiah Flaga's blog https://jeremiahflaga.github.io/

About OpenAPI:
* OpenAPI v3.0.1 Specifications : https://spec.openapis.org/oas/v3.0.1
* Spring generator options: https://openapi-generator.tech/docs/generators/spring
* Java client generator options: https://openapi-generator.tech/docs/generators/java

About code coverage:
* Jacoco maven plugin: https://www.eclemma.org/jacoco/trunk/doc/maven.html
* Coveralls : https://coveralls.io
<a name="status"></a>
## Status
[![Build Status](https://travis-ci.com/CedN/hangman.svg?branch=main)](https://travis-ci.com/CedN/hangman) [![Coverage Status](https://coveralls.io/repos/github/CedN/hangman/badge.svg?branch=main)](https://coveralls.io/github/CedN/hangman?branch=main)
