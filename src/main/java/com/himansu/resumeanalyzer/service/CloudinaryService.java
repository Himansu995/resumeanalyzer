package com.himansu.resumeanalyzer.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary)
    {
        this.cloudinary = cloudinary;
    }

    public Map<String, String> uploadFile(MultipartFile file)
    {

        try
        {

            Map<?, ?> result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "raw",
                            "folder", "resume-analyzer"
                    )
            );

            Map<String, String> response = new HashMap<>();

            response.put(
                    "url",
                    result.get("secure_url").toString()
            );

            response.put(
                    "publicId",
                    result.get("public_id").toString()
            );

            return response;

        }
        catch (Exception e)
        {
            throw new RuntimeException(
                    "Unable to upload file.",
                    e
            );
        }

    }

    public void deleteFile(String publicId)
    {

        try
        {

            cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.asMap(
                            "resource_type",
                            "raw"
                    )
            );

        }
        catch (Exception e)
        {
            throw new RuntimeException(
                    "Unable to delete file.",
                    e
            );
        }

    }

}