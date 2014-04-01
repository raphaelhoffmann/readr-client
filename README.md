readr-client
============

readr-client comprises Java client implementations of readr's RESTful
APIs. There are three such APIs:

1. `service-db`  Used to directly manipulate and query readr's backend
   database. Supports crud operations on projects, documents, document
   annotations, and frames.

2. `service-interactive`  Used to interactively run pipelines of 
   pre-packaged annotators on a document or sentence level. Annotators
   include the Stanford NLP tools. Also used to refresh indices.

3. `service-hadoop`  Used to run pipelines of pre-packaged annotators
   or create indices in batch mode on a hadoop cluster. The output
   can be imported into readr's backend database through `service-db`.


The following example shows how to use the `service-db` client. Analog
steps allow using the other clients.

Start by creating a Maven project and adding the following dependency
to your `pom.xml`.

```xml
  <dependencies>
    <dependency>
      <groupId>com.readr</groupId>
      <artifactId>service-db-client</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>
  </dependencies>

  <repositories>
    <repository>
        <id>readr-client-mvn-repo</id>
        <url>https://raw.github.com/raphaelhoffmann/readr-client/mvn-repo/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
  </repositories>
```

The following Java code creates and deletes a project.

```java
package com.readr.service.db.client;

import com.readr.model.module.text.Text;

public class Test {
	
	public static void main(String[] args) throws Exception {
		
		ReadrDBClient client = new ReadrDBClient();
		
		client.authenticate("test", "test");
		
		Text t = client.text.document.get("allenai", "barrons", 70);
		
		System.out.println(t.text);
	}
}
```





