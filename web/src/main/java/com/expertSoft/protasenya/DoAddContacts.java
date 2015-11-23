package com.expertSoft.protasenya;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

public class DoAddContacts extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PersonService personService = new PersonService();
    String path;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("admissionForAdding", "admissionForAdding");
        ServletInputStream in = req.getInputStream();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        ServletOutputStream out = null;

        try {
            List items = upload.parseRequest(req);
            Iterator iter = items.iterator();
            resp.setContentType("text/html");
            out = resp.getOutputStream();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                } else {
                    File directory = (File) getServletContext().getAttribute("javax.servlet.context.tmpdir");
                    File file = File.createTempFile("tmp", ".csv", directory);
                    file.deleteOnExit();
                    path = file.getAbsolutePath();
                    item.write(file);
                }
            }
            personService.add(path);
            req.getSession().removeAttribute("admissionForAdding");
            resp.sendRedirect(resp.encodeRedirectURL("displayContacts"));
            out.close();
        } catch (FileUploadException fue) {
            fue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
