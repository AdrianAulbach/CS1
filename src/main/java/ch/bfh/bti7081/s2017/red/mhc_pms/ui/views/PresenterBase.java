package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import org.apache.log4j.Logger;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.MvpView;

/**
 * @author Samuel Egger
 * @param <TView>
 */
public abstract class PresenterBase<TView extends MvpView> {

    static final Logger LOGGER = Logger.getRootLogger();

    private final TView view;

    public PresenterBase(TView view) {
        this.view = view;
    }

    public TView getView() {
        return view;
    }
}
