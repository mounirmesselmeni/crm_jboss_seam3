/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller;

import java.io.*;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Named
@SessionScoped
public class FileUploadController implements Serializable {

    private static final int BUFFER_SIZE = 6124;
    private UploadedFile file = null;
    private StreamedContent image = null;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload(String path) throws IOException {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);

            byte[] buffer = new byte[BUFFER_SIZE];

            int bulk;
            InputStream inputStream;
            if (file != null) {
                inputStream = file.getInputstream();


                while (true) {
                    bulk = inputStream.read(buffer);
                    if (bulk < 0) {
                        break;
                    }
                    fileOutputStream.write(buffer, 0, bulk);
                    fileOutputStream.flush();
                }

                fileOutputStream.close();
                inputStream.close();

                FacesMessage msg = new FacesMessage("Succesful", "File :" + file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            resetFile();
            }

        } catch (IOException e) {
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "File was not uploaded! : " + e, "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }

    public void upload(String path, UploadedFile file) throws IOException {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream;
            if (file != null) {
                inputStream = file.getInputstream();


                while (true) {
                    bulk = inputStream.read(buffer);
                    if (bulk < 0) {
                        break;
                    }
                    fileOutputStream.write(buffer, 0, bulk);
                    fileOutputStream.flush();
                }

                fileOutputStream.close();
                inputStream.close();

                FacesMessage msg = new FacesMessage("Succesful", "File :" + file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            resetFile();
            }
        } catch (IOException e) {
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "File was not uploaded! : " + e, "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }
    public void uploadFromURL(String url, String destinationPath) {
        try {

            File inputFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(url));
            File outputFile = new File(destinationPath);

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile));

            int c;

            while ((c = bis.read()) != -1) {
                bos.write(c);
            }

            bis.close();
            bos.close();
            resetFile();
        } catch (IOException ex) {

            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "File was not uploaded! : " + ex, "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }

    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            file = event.getFile();
            image = new DefaultStreamedContent(file.getInputstream(), event.getFile().getContentType());
        } catch (Exception ex) {
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Can't dispaly image : " + ex, "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }

    public StreamedContent getImage() {
        return image;
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }

    public void resetFile() {
        file = null;
        image = null;
    }
}