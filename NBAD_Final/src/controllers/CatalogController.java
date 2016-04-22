/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Utility.ProductDB;
import beans.Product;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author siddharth
 */
public class CatalogController extends HttpServlet {
    
    ProductDB prodb;
    @Override
    public void init() throws ServletException {
        prodb= new ProductDB();
    }

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productCode = request.getParameter("productCode");
        if(productCode!=null){
             
            Product product = prodb.getProduct(productCode);
            if(product!=null){
                request.setAttribute("product", product);
                getServletContext().getRequestDispatcher("/item.jsp").forward(request, response);
            }
            else{
                getServletContext().getRequestDispatcher("/catalog.jsp").forward(request, response);
            }
            return;
        }
        
        String catalogCategory = request.getParameter("catalogCategory");
        if(catalogCategory!=null){
            if(prodb.getProducts(catalogCategory).size()>0){
                // if catalogCategory is valid
                request.setAttribute("catalogCategory", catalogCategory);
            }
                getServletContext().getRequestDispatcher("/catalog.jsp").forward(request, response);
                return;
        }
        
        request.setAttribute("catalogCategory","All");
        getServletContext().getRequestDispatcher("/catalog.jsp").forward(request, response);

        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
