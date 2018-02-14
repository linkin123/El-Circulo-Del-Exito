package cinepoilisklic.com.ia.elcirculodelexito.data.retrofit.services;


import java.util.List;

import cinepoilisklic.com.ia.elcirculodelexito.data.modelsService.StudentsResponse;
import io.reactivex.Observable;

/**
 * Created by lrangel on 13/02/2018.
 */

public interface StudentsRetrofitService {

    Observable<List<StudentsResponse>> getStudents();

}
