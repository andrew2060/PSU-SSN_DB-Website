package edu.pdx.ssn.pages.types;

import edu.pdx.ssn.Params;
import edu.pdx.ssn.Server;
import edu.pdx.ssn.application.Book;
import edu.pdx.ssn.pages.ServerPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class CatalogPage implements ServerPage {

    public static final String PAGE_KEY = "catalog";



    @Override
    public boolean processRequest(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> params = req.getParameterMap();
        Long isbn = params.containsKey(Params.ISBN.getKey()) ? Long.valueOf(params.get(Params.ISBN.getKey())[0]) : null;
        String title = params.containsKey(Params.TITLE.getKey()) ? params.get(Params.TITLE.getKey())[0].toLowerCase() : null;
        String last = params.containsKey(Params.AUTHOR_LAST.getKey()) ? params.get(Params.AUTHOR_LAST.getKey())[0].toLowerCase() : null;
        String first = params.containsKey(Params.AUTHOR_FIRST.getKey()) ? params.get(Params.AUTHOR_FIRST.getKey())[0].toLowerCase() : null;
        String subj = params.containsKey(Params.SUBJECT.getKey()) ? params.get(Params.SUBJECT.getKey())[0].toLowerCase() : null;
        Integer courseno;
        if (params.containsKey(Params.COURSE.getKey()))
            courseno = Integer.valueOf(params.get(Params.COURSE.getKey())[0]);
        else {
            courseno = null;
        }
        List<Book> coll = Server.getLibrary().getCatalog(isbn, title, last, first, subj, courseno);
        req.setAttribute("books", coll);
        return false;
    }

    @Override
    public void setMetaAttributes(HttpServletRequest req) {
        req.setAttribute("title", "Catalog");
        req.setAttribute("app", PAGE_KEY);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }
}
