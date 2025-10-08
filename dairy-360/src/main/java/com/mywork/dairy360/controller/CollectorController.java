package com.mywork.dairy360.controller;

public class CollectorController {
    public CollectorController(){
        System.out.println("no arg constructor of collector");
    }

    public String redirectToCollect(){
        System.out.println("redirecting to milk collect page");
        return "milk";
    }
}
