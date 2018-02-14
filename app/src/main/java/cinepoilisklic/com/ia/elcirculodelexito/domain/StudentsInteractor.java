package cinepoilisklic.com.ia.elcirculodelexito.domain;

import android.content.Context;

import org.reactivestreams.Subscription;

import java.util.List;

import cinepoilisklic.com.ia.elcirculodelexito.data.modelsService.StudentsResponse;
import cinepoilisklic.com.ia.elcirculodelexito.data.retrofit.services.StudentsRetrofitService;
/*import rx.Subscription;*/
/**
 * Created by lrangel on 13/02/2018.
 */

public class StudentsInteractor {

    private OnStudentsListener listener;
    private Context context;
    private Subscription subscription;
    private StudentsRetrofitService service;

    public StudentsInteractor(Context context, StudentsRetrofitService service) {
        this.context = context;
        this.service = service;
    }


    public void setListener( StudentsInteractor.OnStudentsListener listener ){
        this.listener = listener;
    }

    public interface OnStudentsListener{
        void onError(String mensaje);
        void onSendStudents(List<StudentsResponse> students);
    }
}
