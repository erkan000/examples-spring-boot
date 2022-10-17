JoinFaces War Example

http://joinfaces.org/

http://localhost:8080/welcome.jsf



### pom.xml

Includes joinfaces starter dependency. All other jsf dependencies are included transitively.

```xml
<properties>
   <joinfaces.version>4.7.0</joinfaces.version>
</properties>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.joinfaces</groupId>
            <artifactId>joinfaces-dependencies</artifactId>
            <version>${joinfaces.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependencies>
  <dependency>
    <groupId>org.joinfaces</groupId>
    <artifactId>jsf-spring-boot-starter</artifactId>
  </dependency>
</dependencies>
```