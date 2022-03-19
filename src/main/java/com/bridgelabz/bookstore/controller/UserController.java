package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.dto.VerifyUser;
import com.bridgelabz.bookstore.services.MyEmailService;
import com.bridgelabz.bookstore.services.UserService;
import com.bridgelabz.bookstore.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MyEmailService emailService;

    /*
       Purpose : Get All the Users registered in the Database
     */

    @GetMapping(value = {"", "/"})
    ResponseEntity<ResponseDTO> getUsers(){
        return new ResponseEntity<ResponseDTO> (new ResponseDTO("Get Call Success",
                userService.getUsers()), HttpStatus.OK);
    }

    /*
       Purpose : Get All the Users registered in the Database based on UserId

     */

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseDTO> getUserbyId(@PathVariable("userId") Long userId) {
        return new ResponseEntity<ResponseDTO>( new
                ResponseDTO("Get Call By Id Success",
                userService.getUserById(userId)), HttpStatus.OK);
    }

    /*
       Purpose : Get All the Users registered in the Database based on Email-ID
     */

    @GetMapping("/useremail/{emailId}")
    public ResponseEntity<ResponseDTO> getUserByEmailId(@PathVariable("emailId") String emailId) {
        return new ResponseEntity<ResponseDTO>( new
                ResponseDTO("Get Call By Id Success",
                userService.getUserByEmailId(emailId)), HttpStatus.OK);
    }

    /*
       Purpose : To add a new User in the database
     */

    @PostMapping("/adduser")
    ResponseEntity<Response> addUser(@RequestBody UserDTO userDTO){
        Response response = userService.addUser(userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
       Purpose : Ability to Login to the webpage using Username and Password
     */

    @PostMapping("/login")
    public ResponseEntity<Response> userLogin(@RequestParam(name = "email") String email_id, @RequestParam String psw) {
        Response resp = userService.loginUser(email_id, psw);
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    /*
       Purpose : Reset the password using Token
     */

    @PostMapping("/forgotpsw")
    public ResponseEntity<Response> forgotPassword(@RequestParam(name = "token") String token, @RequestParam String psw) {
        Response resp = userService.forgotPassword(token, psw);
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    /*
       Purpose : Verify thr USer using OTP
     */

    @PostMapping("/verifyuser")
    ResponseEntity<Response> verifyUser(@RequestBody VerifyUser verifyUser){
        Response response = userService.verifyUser(verifyUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
       Purpose : Sending the mail to the registered user
     */

    @PostMapping("send")
    public String sendEmail(HttpServletRequest req) {
        emailService.sendOTPMessage("srinathkalai6414@gmail.com", "Email testing", "you registered successfully");
        return "hello";
    }

    /*
       Purpose : Update the user details in the database
     */


    @PutMapping("/updateuser/{token}")
    ResponseEntity<Response> updateUser(@PathVariable("token") String token,
                                        @RequestBody UserDTO userDTO){
        Response resp = userService.updateUser(token, userDTO);
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    /*
       Purpose : Delete the user details in the Database
     */

    @DeleteMapping("/delete/{token}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("token") String token) {
        userService.deleteUser(token);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("deleted adressBook data with personId :", token),
                HttpStatus.OK);
    }
}
