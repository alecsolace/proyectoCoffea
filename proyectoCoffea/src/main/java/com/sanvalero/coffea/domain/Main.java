/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.coffea.domain;

/**
 *
 * @author aaguirrem
 */
public class Main {

    public static void main(String[] args) {
        /* CartLineDAO cartLinesDAO = new CartLineDAO();

        ProductDAO productDAO = new ProductDAO();
        ArrayList<Product> productList = productDAO.get_products();
        ArrayList<CartLine> cartLines;
        cartLines = (ArrayList<CartLine>) application.getAttribute("carrito");
        CartDAO cartDAO = new CartDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        ArrayList<Customer> customerList = customerDAO.getCustomers();
        DecimalFormat df = new DecimalFormat("0.00");

        if (cartLines == null) {
            cartLines = new ArrayList<>();
        }

        int selectedProductID = Integer.parseInt(request.getParameter("param"));

        int totalPrice = 0;

        for (Customer customer : customerList) {
            if (customer.getUserID() == 1) {

                Cart cart = new Cart(cartDAO.getCarts().size(), customer, totalPrice, new Date(2022, 02, 24));

            }
        }
        if (cartLines != null) {

            for (Product productS : productList) {

                if (productS.getProductID() == selectedProductID) {

                    for (CartLine cartLine : cartLines) {
                        if (productS.getProductID() == cartLine.getProduct().getProductID()) {
                            cartLine.setQuantity(cartLine.getQuantity() + 1);
                            cartLine.setPrice(2 * cartLine.getPrice());
                            application.setAttribute("carrito", cartLines);
                        } else {
                            Product productAdded = productS;
                            CartLine cartLine = new CartLine((cartLines.size() + 1), productAdded, 1,
                                    productAdded.getPrice());
                            cartLines.add(cartLine);
                            application.setAttribute("carrito", cartLines);
                        }
                    }

                    //application.setAttribute("carrito", cartLines);
                }

            }

            for (CartLine cartLineProduct : cartLines) {
                for (Product product : productList) {
                    if (product.getProductID() == cartLineProduct.getProduct().getProductID()) {
                        System.out.println("Producto en carrito");
                    }
                }
                totalPrice += cartLineProduct.getPrice();
            }
        }*/
    }
}
