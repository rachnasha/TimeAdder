This file describes the code setup and instructions to build and run this code

Build and run this project

- run sbt from the root of the project which will bring a sbt prompt.

    [~/TimeAdder]$ sbt
    sbt:TimeApp>

- Run app :From the prompt you can run the app as shown below

   sbt:TimeApp> run "9:13 PM" "-700"
   [info] running affinypay.TimeApp "9:13 PM" -700
   9:33 AM

- To exit: simply type exit at the prompt
    sbt:TimeApp> exit
    [info] shutting down sbt server

- How to run test : from the root of the project run - sbt test

    [~/TimeAdder]$ sbt test
    .....
    [info] Run completed in 433 milliseconds.
    [info] Total number of tests run: 13
    [info] Suites: completed 3, aborted 0
    [info] Tests: succeeded 13, failed 0, canceled 0, ignored 0, pending 0
    [info] All tests passed.

