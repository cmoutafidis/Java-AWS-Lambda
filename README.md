# Java-AWS-Lambda
An example project that runs a Java Lambda Function as a local API.

## Usage
To run the project as a local api use:
```
sam local start-api -t template.yml
```
In case of local changes, you need to build the project and restart the sam api.

The project was build using IntelliJ. You can use the AWS Toolkit for IntelliJ to run each function idividually.
