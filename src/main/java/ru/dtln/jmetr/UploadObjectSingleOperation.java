package ru.dtln.jmetr;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class UploadObjectSingleOperation {
    private static String ENDPOINT = "http://msk.s3.dtln.ru";
    private static String BUCKET_NAME = "newbucket";
    private static String KEY_NAME = "default";
    private static String UPLOAD_FILENAME = "/jmeter.7z";
    private static String REG = "msk"; // таких регионов нет!


    public static void main(String[] args) throws IOException {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.ACCESS_KEY_ID, Credentials.SECRET_ACCESS_KEY);
        Region usWest2 = Region.getRegion(Regions.US_EAST_1);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                //      .withRegion("us-west-1") // TODO: Узнай свой регион где у тебя есть твой "newbucket"!
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
        try {
            System.out.println("Uploading a new object to S3 from a file\n");
            URL url = UploadObjectSingleOperation.class.getResource("/jmeter.7z");
            File file = new File(url.getPath());
            s3Client.putObject(new PutObjectRequest(
                    BUCKET_NAME, KEY_NAME, file));

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }
}
