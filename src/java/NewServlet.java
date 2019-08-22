/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pkc Prashant
 */
public class NewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAccessControlHeaders(response);
        try (PrintWriter out = response.getWriter()) {
            
            JSONArray array=new JSONArray();
            JSONObject object;
            
            Enumeration<String> parameterNames = request.getParameterNames();
 
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                JSONObject jObj = new JSONObject(paramName); // this parses the json
                Iterator it = jObj.keys(); //gets all the keys

                while(it.hasNext())
                {
                    String key = (String)it.next(); // get key
                    String value = (String)jObj.get(key); // get value
                    System.out.println("Key : "+key+" , Value : "+value);
                }
            }
            ArrayList<UserDTO> users = UserDAO.getUser();
            for(UserDTO user : users){
                object = new JSONObject();
                object.put("id", user.getId());
                object.put("name", user.getName());
                array.put(object);
            }
            out.println(array);
        } catch (JSONException | SQLException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setAccessControlHeaders(HttpServletResponse resp) {
      resp.addHeader("Access-Control-Allow-Origin", "*");
      resp.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
 }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
