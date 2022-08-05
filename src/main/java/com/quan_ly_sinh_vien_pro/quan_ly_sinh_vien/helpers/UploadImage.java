package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.xml.stream.events.DTD;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UploadImage {
    public static Boolean CheckPartNull(HttpServletRequest req, String partName) {
        try {
            Part part = req.getPart(partName);
            String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
            return fileName.equals("");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void SaveToLocal(HttpServletRequest req, String partName) {
        try {
            if (!Objects.equals(partName, "")) {
                Part part = req.getPart(partName);
                String realPath = req.getServletContext().getRealPath("/Uploads");
                String fileName = UploadImage.GetTimeStringToUpload() + "-" + Path.of(part.getSubmittedFileName()).getFileName().toString();
                Path path = Path.of(realPath);
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                    System.out.println("Upload folder not found! we must create new dir!");
                }
                part.write(realPath + "/" + fileName);
            } else {
                System.out.println("Part name not null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String GetTimeStringToUpload() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HH");
            return simpleDateFormat.format(new Date());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String GetFileName(HttpServletRequest req, String partName) {
        try {
            if (!Objects.equals(partName, "")) {
                Part part = req.getPart(partName);
                return UploadImage.GetTimeStringToUpload() + "-" + Path.of(part.getSubmittedFileName()).getFileName().toString();
            } else {
                System.out.println("Part name not null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }
}
