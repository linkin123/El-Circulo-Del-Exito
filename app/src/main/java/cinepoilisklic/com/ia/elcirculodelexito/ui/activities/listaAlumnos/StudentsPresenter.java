package cinepoilisklic.com.ia.elcirculodelexito.ui.activities.listaAlumnos;

import com.google.gson.Gson;

import java.util.List;

import cinepoilisklic.com.ia.elcirculodelexito.data.modelsService.StudentsResponse;
import cinepoilisklic.com.ia.elcirculodelexito.domain.StudentsInteractor;

/**
 * Created by lrangel on 13/02/2018.
 */

public class StudentsPresenter implements StudentsInteractor.OnStudentsListener{

    private StudentsInteractor interactor;

    private Gson gson;
    public StudentsPresenter(StudentsInteractor interactor){
        this.interactor = interactor;
        this.interactor.setListener(this);
    }
    @Override
    public void onError(String mensaje) {

    }

    @Override
    public void onSendStudents(List<StudentsResponse> students) {

    }



    public interface View{
        void onDataStudentsSent(List<StudentsResponse> studentsResponseList);
        void onSercviceError(String error);

    }
}
