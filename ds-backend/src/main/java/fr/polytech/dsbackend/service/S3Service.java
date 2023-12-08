package fr.polytech.dsbackend.service;

import org.springframework.stereotype.Service;

import fr.polytech.dsbackend.exception.ResourceBadRequestException;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class S3Service {
    
    private final MinioClient minioClient;

    private String getPresignedUrl(Method method, String bucketName, String objectName) {
        try {
            return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .method(method)
                    .bucket(bucketName)
                    .object(objectName)
                    .build()
            );
        }catch (Exception e){
            throw new ResourceBadRequestException(e.getMessage());
        }
    }

    public String getImageUrl(Integer id, String acteurBucket) {
        return this.getPresignedUrl(Method.GET, acteurBucket, String.valueOf(id));
    }

    public String putImageUrl(Integer id, String acteurBucket) {
        return this.getPresignedUrl(Method.PUT, acteurBucket, String.valueOf(id));
    }
}
