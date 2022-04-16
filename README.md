# batch-insert-jdbc-fragment
[![Build Status](https://app.travis-ci.com/anickol/batch-insert-jdbc-fragment.svg?branch=main)](https://app.travis-ci.com/anickol/batch-insert-jdbc-fragment)

This repository hosts a [Spring Data JDBC fragment][spring-data-jdbc-fragment], which implements faster batch inserts for [Spring Data JDBC Repositories][spring-data-jdbc-repository].
The performance improvement is achieved by bypassing tests and management done by the Spring Data JDBC framework and the usage of simple [Spring JDBC][spring-framework] core features. 

This shortcut can only be used for simple datastructures, not having any one-to-many or one-to-one references to any other entity.

The purpose of this library is to allow faster insertion of simple datastructures into a relational database.

[spring-data-jdbc-repository]: https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#jdbc.repositories
[spring-data-jdbc-fragment]: http://creativecommons.org/publicdomain/zero/1.0/legalcode
[spring-framework]: https://github.com/spring-projects/spring-framework

## Dependencies

The library depends on Spring Data JDBC and uses Spring Boot Auto-Configurations.

```xml
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-autoconfigure</artifactId>
</dependency>
```

The library has been tested with the following databases:

- [H2](https://www.h2database.com/html/main.html)
- [PostgreSQL](https://www.postgresql.org/)

## Installation

With maven, you can install the library by adding the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>de.anickol</groupId>
    <artifactId>batch-insert-jdbc-fragment</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Configuration

The auto-configuration of the library can be customized using the following property:

```property
de.anickol.batch_insert_jdbc_fragment.enabled = true|false (default: true)
```

## Usage

Just add the interface `InsertAll` to the repository where you want to use the fragment.

```java
import de.anickol.batch_insert_jdbc_fragment.InsertAll;
import org.springframework.data.repository.CrudRepository;
        
public interface MyCrudRepository extends CrudRepository<T, ID>, InsertAll<T> {};
```

## How to test the software

The unit-tests of this repository can be used to test the functionality of this library. 

## Known issues

There are currently no know issues with this library.

## Getting help

Please you the issue tracker of the github repository if you have any problems using the library.

## Getting involved

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

For more information see [CONTRIBUTING](CONTRIBUTING.md).


## License

This software library is released under version 2.0 of the Apache License.
