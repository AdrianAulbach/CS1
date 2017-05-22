package ch.bfh.bti7081.s2017.red.mhc_pms.presenter;

/**
 * Created by Rolf on 22/05/17.
 */
public abstract class PresenterBase<TView> {
    private TView view;

    public PresenterBase(TView view){
        this.view = view;
    }

    protected TView getView(){
        return  this.view;
    }
}
