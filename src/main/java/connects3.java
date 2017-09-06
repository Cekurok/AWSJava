import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.File;

public class connects3 {
    private static String endpoint       = "http://msk.s3.dtln.ru";
    private static String bucketName     = "newbucket";
    private static String keyName        = "default";
    private static String uploadFileName = "E:\\Project\\Learn\\S3pinok\\s3jmeterplugin\\src\\main\\resources\\jmeter.7z";
    private static String reg            = "msk";


    public static void main(String[] args) throws IOException {


        try {
            AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();
            //AmazonS3 s3client = AmazonS3ClientBuilder.standard().build();
            s3client.setEndpoint(endpoint);
            s3client.setRegion(Region.getRegion(Regions.DEFAULT_REGION));
            GetBucketLocationRequest bucketLocationRequest = new GetBucketLocationRequest(bucketName);
            System.out.println(bucketLocationRequest);
            System.out.println("Uploading a new object to S3 from a file\n");
            File file = new File(uploadFileName);
            s3client.putObject(new PutObjectRequest(
                    bucketName, keyName, file));

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
