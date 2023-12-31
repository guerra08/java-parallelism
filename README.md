# java-parallelism
Examples and usage of parallelism concepts in modern Java

## Technical details

- Java 17
- Gradle
- GraalVM's compilation option

The application processes the files in the  directory, and computes which character is the most frequent for each file.

## Usage

You can create the files used in the test by executing the following command:
```shell
./create-samples.sh
```

This will create the 500 sample files in $HOME/tmp/sample-files. Feel free to customize it!

You can compile the application by running:

```shell
./gradlew build
```

Then, execute it with:

```shell
java -jar build/libs/java-parallelism-1.0-SNAPSHOT.jar
```

If you want to test a sequential version, run with the 's' argument:

```shell
java -jar build/libs/java-parallelism-1.0-SNAPSHOT.jar s
```

You can also compile the application to a native executable with GraalVM by running:
```shell
./gradlew nativeCompile
```