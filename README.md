This is a project which reproduces the Mojarra issue
<a href="https://github.com/eclipse-ee4j/mojarra/issues/4394">number #4394</a>.

You can execute the application with <strong>mvn jetty:run</strong> command.
Then you can go to <strong>http://localhost:8081/mojarra-bug-test-case</strong>
to load the page which shows the error.

Another possibility is to run the automated test with
<strong>mvn integration-test</strong> command.

The error can be reproduced using the maven profiles "mojarra22" (default, uses
Mojarra 2.2.x) and "mojarra23" (uses Mojarra 2.3.x).

The profiles "myfaces22" and "myfaces23" are also available, to
show that in MyFaces this bug doesn't happen.
