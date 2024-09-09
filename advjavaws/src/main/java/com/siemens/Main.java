package com.siemens;

public class Main {
    public static void main(String[] args) {
       //lambda expression for runnable interface
       Runnable runnable=()->{
           //logic
          String name="Parameswari";
          for(char ch : name.toCharArray()){
              System.out.print(ch);
              try {
                  Thread.sleep(2000);
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }
       };
       Thread thread=new Thread(runnable);
       thread.start();


    }
}