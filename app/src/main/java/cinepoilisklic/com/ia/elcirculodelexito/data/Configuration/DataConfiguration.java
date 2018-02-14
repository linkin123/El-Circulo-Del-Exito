package cinepoilisklic.com.ia.elcirculodelexito.data.Configuration;

/**
 * Created by lrangel on 13/02/2018.
 */

public class DataConfiguration {

    public static final  String BASE_URL = "http://demo4643202.mockable.io";

    private String baseUrl;

    public DataConfiguration(String baseUrl){ this.baseUrl = baseUrl; }

    public String getBaseUrl(){ return baseUrl; }

    public static class StudentsApi{
        public static final String STUDENTS = "http://demo4643202.mockable.io/v1/Alumns";
    }


}
