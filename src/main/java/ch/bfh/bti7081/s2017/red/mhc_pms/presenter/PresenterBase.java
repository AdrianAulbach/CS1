package ch.bfh.bti7081.s2017.red.mhc_pms.presenter;

import com.vaadin.navigator.Navigator;

/**
 * Created by Rolf on 22/05/17.
 */
public abstract class PresenterBase<TView> {
    private TView view;
    private Navigator navigator;

    public PresenterBase(TView view, Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    protected TView getView() {
        return this.view;
    }

    public void onInitialize() {
    }

    public void navigateTo(String path){
        this.navigator.navigateTo(path);
    }

}
