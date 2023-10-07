package com.example.newsapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIServices {

   private static Retrofit retrofit=null;
   public static APIInterface getAPIInterface(){
      if(retrofit==null){
         retrofit=new Retrofit.Builder().baseUrl(APIInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                 .build();
      }
      return retrofit.create(APIInterface.class);

   }

}
