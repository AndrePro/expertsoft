package com.expertSoft.protasenya;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 06.11.2015.
 */
public class DisplayAllContacts extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PersonService personService = new PersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 10;
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
        List<Person> contacts = new ArrayList<>();
        contacts = personService.getAll((page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = personService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        req.setAttribute("contacts", contacts);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.getRequestDispatcher("/WEB-INF/jsp/displayContacts.jsp").forward(req, resp);
    }

}
