package fr.polytech.dsbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.polytech.dsbackend.exception.ResourceBadRequestException;
import io.minio.MinioClient;
import io.minio.http.Method;

public class S3ServiceTest {

    @Mock
    private MinioClient minioClient;

    @InjectMocks
    private S3Service s3Service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetImageUrl() throws Exception {
        // Paramètres fictifs
        Integer id = 1;
        String acteurBucket = "exocover";
        String expectedUrl = "http://example.com/image";

        when(minioClient.getPresignedObjectUrl(any())).thenReturn(expectedUrl);

        String result = s3Service.getImageUrl(id, acteurBucket);

        assertEquals(expectedUrl, result);

        verify(minioClient).getPresignedObjectUrl(argThat(arg ->
                arg.method() == Method.GET &&
                arg.bucket().equals(acteurBucket) &&
                arg.object().equals(String.valueOf(id))));
    }

    @Test
    public void testPutImageUrl() throws Exception {
        // Paramètres fictifs
        Integer id = 1;
        String acteurBucket = "exocover";
        String expectedUrl = "http://example.com/image";

        when(minioClient.getPresignedObjectUrl(any())).thenReturn(expectedUrl);

        String result = s3Service.putImageUrl(id, acteurBucket);

        assertEquals(expectedUrl, result);

        verify(minioClient).getPresignedObjectUrl(argThat(arg ->
                arg.method() == Method.PUT &&
                arg.bucket().equals(acteurBucket) &&
                arg.object().equals(String.valueOf(id))));
    }

    @Test
    public void testGetImageUrlException() throws Exception {
        // Paramètres fictifs
        Integer id = 1;
        String acteurBucket = "exocover";

        when(minioClient.getPresignedObjectUrl(any())).thenThrow(new RuntimeException("Some Minio Exception"));

        ResourceBadRequestException exception = assertThrows(ResourceBadRequestException.class, () -> {
            s3Service.getImageUrl(id, acteurBucket);
        });

        assertEquals("Some Minio Exception", exception.getMessage());
    }

}
