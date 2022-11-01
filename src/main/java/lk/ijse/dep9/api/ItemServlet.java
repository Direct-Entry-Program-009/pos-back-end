package lk.ijse.dep9.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lk.ijse.dep9.dto.CustomerDTO;
import lk.ijse.dep9.util.HTTPServlet2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name = "ItemServlet", value = "/items")
public class ItemServlet extends HTTPServlet2 {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }



    @Override
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super.doPatch(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
    private void loadAllItems(HttpServletResponse response) throws SQLException, IOException {
    }

    private void searchPaginatedItems(String query,int size, int page, HttpServletResponse response){

    }
    private void getItemDetails(String customerId, HttpServletResponse response){

    }
}
