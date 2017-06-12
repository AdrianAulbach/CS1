package ch.bfh.bti7081.s2017.red.mhc_pms.presenter;

import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.IUserSession;

/**
 * Created by Rolf on 22/05/17.
 */
public abstract class PresenterBase<TView> {
    private TView view;
    protected IUserSession session;

    public PresenterBase(TView view, IUserSession session) {
        this.view = view;
        this.session = session;
    }

    protected TView getView() {
        return this.view;
    }

    public void onInitialize() {
    }

    public void navigateTo(String path){
        this.session.getNavigator().navigateTo(path);
    }

}
