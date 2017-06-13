package ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages;

import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.MvpView;

/**
 * The abstract class MainPageContent. Used to describe content of the Main Page
 * of the application. All Main page contents can be updated on request of a new
 * url, this makes the contents reusable and exchangeable.
 *
 * @author Aleistar Markóczy, Samuel Egger
 * @param <TPresenter> the presenter type
 */
public abstract class MainPageContent<TPresenter> extends VerticalLayout implements MvpView<TPresenter> {

    private static final long serialVersionUID = 1L;
    protected TPresenter presenter = null;

    @Override
    public void setPresenter(TPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Update params from an existing param definition.
     *
     * @param params the params
     */
    public abstract void updateParams(PathParams params);

    // TODO resetParams
}
