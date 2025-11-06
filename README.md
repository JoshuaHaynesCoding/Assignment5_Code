assignment 5 – unit, mocking, integration testing

author: joshua haynes

overview

 the goal was to practice writing real unit tests, integration tests, using mocks, and then setting up github actions to automatically run everything. the repo contains the barnesandnoble project tests and the amazon project tests. i also set up ci with checkstyle and jacoco so every push shows the results.

part 1 – barnesandnoble tests

i wrote both specification-based tests and structural-based tests for the barnesandnoble package. these are in the test folder and are labeled using the @displayname tags like the assignment asked.

part 2 – github actions

i created the workflow file inside .github/workflows/se333_ci.yml.
the workflow:
runs on every push to main
runs on ubuntu
runs checkstyle during validate
runs all tests
collects jacoco coverage
uploads the checkstyle and jacoco reports as artifacts

the build badge is below and shows the current status of the workflow:

part 3 – amazon tests

i wrote two test files: amazonintegrationtest and amazonunittest.
integration tests check how everything works together, including cart, items, and rules.
unit tests use mocks to isolate behavior and test just the amazon class logic.
each test file also includes both specification-based and structural-based tests.

workflow link

you can see the workflow runs here:
https://github.com/JoshuaHaynesCoding/Assignment5_Code/actions