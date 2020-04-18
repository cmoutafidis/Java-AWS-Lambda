package com.cmoutafidis.lambdaexample.dao;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.cmoutafidis.lambdaexample.utils.Common;

import java.util.List;

public abstract class Dao<T> {

    private AmazonDynamoDB dynamoDBClient;
    private DynamoDB dynamoDB;
    private final String dynamoDbTableName;

    public Dao(final String dynamoDbTableName) {
        this.dynamoDbTableName = dynamoDbTableName;
        this.dynamoDBClient = this.getDynamoDBClient();
    }

    public String getDynamoDbTableName() {
        return this.dynamoDbTableName;
    }

    public AmazonDynamoDB getDynamoDBClient() {
        if (this.dynamoDBClient != null) {
            return this.dynamoDBClient;
        }
        this.dynamoDBClient = AmazonDynamoDBClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(Common.CONFIG.getProperty("aws.access_key"), Common.CONFIG.getProperty("aws.secret_key"))
        )).withRegion(Common.CONFIG.getProperty("aws.region")).build();

        this.dynamoDB = new DynamoDB(this.dynamoDBClient);
        return this.dynamoDBClient;
    }

    public DynamoDB getDynamoDB() {
        return this.dynamoDB;
    }

    public abstract T get(String id);

    public abstract List<T> getAll();

    public abstract String save(T t);

    public abstract void update(T t, String[] params);

    public abstract void delete(T t);
}