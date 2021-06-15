/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

/**
 *
 * @author root
 */
public class ComputeAge {

    public static String getAge(float age) {

        String realAge = "";
        
        if (age < 0.1){
            realAge = "1 Month";
        } else if (age == 0.2 ){
            realAge = "2 Months";
        }  else if (age == 0.3 ){
            realAge = "3 Months";
        }  else if (age == 0.4 ){
            realAge = "4 Months";
        }  else if (age == 0.5 ){
            realAge = "5 Months";
        }  else if (age == 0.6 ){
            realAge = "6 Months";
        }  else if (age == 0.7 ){
            realAge = "7 Months";
        }  else if (age == 0.8 ){
            realAge = "8 Months";
        }  else if (age == 0.9 ){
            realAge = "9 Months";
        }  else if (age == 0.10 ){
            realAge = "10 Months";
        }  else if (age == 0.11 ){
            realAge = "11 Months";
        } else {
            realAge = String.valueOf(Math.round(age));
        }
        
        return realAge;
        
        }
    

}
