package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.services.IOrderService;
import com.bridgelabz.bookstore.services.MyEmailService;
import com.bridgelabz.bookstore.services.OrderService;
import com.bridgelabz.bookstore.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @Autowired
    MyEmailService emailService;

    /*
       Purpose : To get all the orders by their respective order_id
     */

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable("orderId") Long order_id) {
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Get call success",
                orderService.getBookById(order_id)), HttpStatus.OK);
    }

    /*
       Purpose : To get all the orders placed
     */

    @GetMapping(value = "/getallorders")
    public ResponseEntity<ResponseDTO> getAllOrders() {
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Get call success",
                orderService.getAllOrders()), HttpStatus.OK);
    }

    /*
       Purpose : To get all the orders placed by the specific user
     */

    @GetMapping(value = "getorders/{token}")
    public ResponseEntity<ResponseDTO> getOrderByUser(@PathVariable("token") String token) {
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Get call success",
                orderService.getAllOrderForUser(token)), HttpStatus.OK);
    }

    /*
       Purpose : To Cancel the orders placed by the user
     */

    @PutMapping("cancel/{orderId}")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        System.out.println("order id = "+ orderId);
        orderService.cancelOrder(orderId);
        return "Order cancelled";
    }

    /*
       Purpose : To Place the new order for specific user
     */

    @PostMapping("/add/{token}")
    public ResponseEntity<ResponseDTO> placeOrder(@PathVariable("token") String token, @RequestBody OrderDTO orderDTO) {
        System.out.println("dhgf----> "+orderDTO);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Get call success",
                orderService.placeOrder(token, orderDTO)), HttpStatus.OK);
    }
}
